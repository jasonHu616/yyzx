package com.hy.yyzx.admin.service.security.provider;

import com.hy.yyzx.admin.service.security.authentication.SystemUserAuthToken;
import com.hy.yyzx.admin.service.service.SysLogLoginService;
import com.hy.yyzx.admin.service.service.SysUserService;
import com.hy.yyzx.admin.service.utils.SysConfigUtils;
import com.hy.yyzx.common.model.SysConfig;
import com.hy.yyzx.common.model.SysLogLogin;
import com.hy.yyzx.common.model.SysUser;
import com.hy.yyzx.common.model.SysUserExample;
import com.hy.yyzx.common.modules.MapEntity;
import com.hy.yyzx.common.utils.JSONUtils;
import com.hy.yyzx.common.utils.PassWordHasher;
import com.hy.yyzx.common.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: Jason
 * @DateTime: 2020/4/12 18:43
 * @Description: 登录认证类
 */
public class LoginAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysLogLoginService sysLogLoginService;

    @Autowired
    SysConfigUtils sysConfigUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SystemUserAuthToken authToken = (SystemUserAuthToken)authentication;
        String username = authToken.getUsername();
        String password = authToken.getPassword();
        try {
            //Assert.notNull(username, "用户名不能为空");
            Assert.hasText(username, "请填写用户名");
            Assert.hasText(password, "请填写密码");
        }catch (Exception e){
            throw new BadCredentialsException(e.getMessage());
        }

        SysUserExample userExample=new SysUserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andIsdeleteEqualTo(0)
                .andIsfreezeEqualTo(0).andIscloseEqualTo(0);
        userExample.or(userExample.createCriteria().andPhoneEqualTo(username).andIsdeleteEqualTo(0)
                .andIsfreezeEqualTo(0).andIscloseEqualTo(0));
        List<SysUser> users = sysUserService.selectByExample(userExample);
        if(users!=null && users.size()>0){
            SysUser user=users.get(0);
            String encodePwd = user.getPwd();
            boolean pwdCheck = PassWordHasher.Md5Encoder(password).trim().equals(encodePwd.trim());
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            RequestUtil requestUtil = new RequestUtil(request);
            SysLogLogin logLogin = new SysLogLogin();
            logLogin.setUserid(user.getId());
            logLogin.setIp(requestUtil.getClientIp());
            logLogin.setReferer(requestUtil.getReferer());
            logLogin.setUa(requestUtil.getUserAgent());
            logLogin.setCreatetime(new Timestamp(System.currentTimeMillis()));
            MapEntity post=requestUtil.getPostParams();
            if (!pwdCheck) {
                logLogin.setStatus(0);
                if(!post.getString("password").equals("")){
                    post.put("password",password);
                }
                logLogin.setPostdata(requestUtil.getPostParams()==null ? null : JSONUtils.toJSONString(post));
                //TODO 判断登录错误次数
                user.setErrorcount(user.getErrorcount()+1);
                user.setErrortime(new Timestamp(System.currentTimeMillis()));
                SysConfig loginconfig = sysConfigUtils.getConfigByKey("adminloginerrortime");
                int errortime=0;
                try{
                    if (loginconfig!=null){
                        errortime=Integer.parseInt(loginconfig.getValue());
                    }
                }catch (Exception e){

                }
                if (errortime>0){
                    if (user.getErrorcount()>=errortime){
                        user.setIsclose(1);
                    }
                }
                sysUserService.updateByPrimaryKeySelective(user);
                sysLogLoginService.insert(logLogin);
                throw new BadCredentialsException("密码错误");
            }
            if(!post.getString("password").equals("")){
                post.put("password","******");
            }
            user.setErrorcount(0);
            sysUserService.updateByPrimaryKeySelective(user);
            logLogin.setPostdata(requestUtil.getPostParams()==null ? null :JSONUtils.toJSONString(post));
            SysConfig config = sysConfigUtils.getConfigByKey("allowopenadmin");
            if(config!=null && config.getValue()!=null && !config.getValue().trim().equals("") && Integer.parseInt(config.getValue())==0){
                if(user.getAdmintype().intValue()==2 ||  user.getAdmintype().intValue()==1){
                    logLogin.setStatus(1);
                }else{
                    logLogin.setStatus(0);
                    sysLogLoginService.insert(logLogin);
                    throw new BadCredentialsException("后台已关闭,暂不允许登录");
                }
            }else{
                logLogin.setStatus(1);
            }
            if(user!=null && user.getId().intValue()>-2) {
                sysLogLoginService.insert(logLogin);
            }
            return new SystemUserAuthToken(user.getId(), user.getUsername(), user);
        }else{
            throw new UsernameNotFoundException("用户不存在或已被锁定，请联系管理员");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
