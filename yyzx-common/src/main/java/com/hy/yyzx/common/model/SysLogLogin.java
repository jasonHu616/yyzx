/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户登录日志
 * sys_log_login
 * @date Sun Apr 12 19:22:57 CST 2020
 *
 */
@ApiModel(value = "SysLogLogin", description = "后台用户登录日志")
@SuppressWarnings("serial")
public class SysLogLogin implements Serializable {
    /**
     * 
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 登录用户id
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "登录用户id", allowEmptyValue=true)
    private Integer userid;

    /**
     * 登录状态(0:失败 1:成功)
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "登录状态(0:失败 1:成功)", allowEmptyValue=false)
    private Integer status;

    /**
     * 登录ip地址
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "登录ip地址", allowEmptyValue=false)
    private String ip;

    /**
     * 操作浏览器ua
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作浏览器ua", allowEmptyValue=false)
    private String ua;

    /**
     * 提交的数据
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "提交的数据", allowEmptyValue=false)
    private String postdata;

    /**
     * 来源
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "来源", allowEmptyValue=false)
    private String referer;

    /**
     * 创建时间
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:22:57 CST 2020
     * @return
     */
    @ApiModelProperty(value = "创建时间", allowEmptyValue=false)
    private Date createtime;

    /**
     * @return null : 
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return null : 登录用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid : 登录用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return null : 登录状态(0:失败 1:成功)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status : 登录状态(0:失败 1:成功)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return null : 登录ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip : 登录ip地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return null : 操作浏览器ua
     */
    public String getUa() {
        return ua;
    }

    /**
     * @param ua : 操作浏览器ua
     */
    public void setUa(String ua) {
        this.ua = ua == null ? null : ua.trim();
    }

    /**
     * @return null : 提交的数据
     */
    public String getPostdata() {
        return postdata;
    }

    /**
     * @param postdata : 提交的数据
     */
    public void setPostdata(String postdata) {
        this.postdata = postdata == null ? null : postdata.trim();
    }

    /**
     * @return null : 来源
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer : 来源
     */
    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    /**
     * @return null : 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * @param createtime : 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", status=").append(status);
        sb.append(", ip=").append(ip);
        sb.append(", ua=").append(ua);
        sb.append(", postdata=").append(postdata);
        sb.append(", referer=").append(referer);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}