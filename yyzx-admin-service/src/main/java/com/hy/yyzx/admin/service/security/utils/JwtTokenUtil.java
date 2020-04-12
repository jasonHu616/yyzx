package com.hy.yyzx.admin.service.security.utils;

import com.hy.yyzx.admin.service.config.JwtConfig;
import com.hy.yyzx.admin.service.security.vo.UserClaims;
import com.hy.yyzx.common.utils.RedisUtil;
import com.hy.yyzx.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 18:33
 * @Description: JwtToken工具
 */
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -7032855166562047598L;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 生成令牌
     * @param userClaims 用户摘要
     * @return 令牌
     */
    public String generateToken(UserClaims userClaims) {
        String token = StringUtils.getRandomCode(jwtConfig.getTokenLength(), 1111);
        redisUtil.set("AuthToken:"+token, userClaims, jwtConfig.getExpiration());
        if(jwtConfig.isSingleLogin()) { //单个登录
            Set<String> keys = redisUtil.keys("AuthUser:" + userClaims.getUsername()+":*");
            if(keys!=null && !keys.isEmpty()) {
                for(String key : keys) {
                    key=key.substring(key.indexOf(":")+1);
                    String removetoken = redisUtil.get(key);
                    redisUtil.remove("AuthToken:"+removetoken);
                    redisUtil.remove(key);
                }
            }
        }
        redisUtil.set("AuthUser:" + userClaims.getUsername() + ":" + token, token, jwtConfig.getExpiration());
        return token;
    }


    /**
     * 从令牌中获取用户信息
     *
     * @param token 令牌
     * @return 用户信息
     */
    public UserClaims getUserClaimsFromToken(String token) {
        UserClaims userClaims = redisUtil.get("AuthToken:" + token, UserClaims.class);
        if(userClaims!=null) {
            String authToken = redisUtil.get("AuthUser:" + userClaims.getUsername() + ":" + token);
            if(authToken==null || !authToken.equals(token)){
                if(authToken!=null)redisUtil.remove("AuthUser:" + userClaims.getUsername() + ":" + token);
                userClaims = null;
            }
        }
        return userClaims;
    }

    /**
     * 从令牌中获取username
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            UserClaims userClaims = getUserClaimsFromToken(token);
            if(userClaims!=null)username = userClaims.getUsername();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            UserClaims userClaims = redisUtil.get("AuthToken:" + token, UserClaims.class);
            return userClaims==null;
        } catch (Exception e) {
            return false;
        }
    }

    public void removeToken(String token){
        UserClaims userClaims = redisUtil.get("AuthToken:" + token, UserClaims.class);
        if(userClaims!=null && !userClaims.equals("")){
            String cacheUser = redisUtil.get("AuthUser:" + userClaims.getUsername()+":" + token);
            redisUtil.remove("AuthToken:" + token);
            redisUtil.remove("AuthUser:" + userClaims.getUsername()+":" + token);
        }
    }

    public boolean expireToken(String token){
        UserClaims userClaims = redisUtil.get("AuthToken:" + token, UserClaims.class);
        if(userClaims!=null) {
            String authToken = redisUtil.get("AuthUser:" + userClaims.getUsername() + ":" + token);
            if(authToken==null || !authToken.equals(token)){
                redisUtil.remove("AuthToken:" + token);
                if(authToken!=null)redisUtil.remove("AuthUser:" + userClaims.getUsername() + ":" + token);
                return false;
            }
            return (
                    redisUtil.expire("AuthToken:" + token, jwtConfig.getExpiration()) &&
                            redisUtil.expire("AuthUser:" + userClaims.getUsername() + ":" + token, jwtConfig.getExpiration())
            );
        }
        return false;
    }
    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        UserClaims userClaims = redisUtil.get("AuthToken:" + token, UserClaims.class);
        if(userClaims!=null) {
            redisUtil.remove("AuthToken:" + token);
            return generateToken(userClaims);
        }
        return null;
    }

    /**
     * 验证令牌
     * @param token       令牌
     * @param userClaims 用户
     * @return 是否有效
     */
    public Boolean validateToken(String token, UserClaims userClaims) {
        String openid = getUsernameFromToken(token);
        return (openid.equals(userClaims.getUsername()) && !isTokenExpired(token));
    }
}
