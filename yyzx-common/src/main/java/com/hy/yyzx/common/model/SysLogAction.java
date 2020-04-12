/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 后台用户操作日志
 * sys_log_action
 * @date Sun Apr 12 19:46:14 CST 2020
 *
 */
@ApiModel(value = "SysLogAction", description = "后台用户操作日志")
@SuppressWarnings("serial")
public class SysLogAction implements Serializable {
    /**
     * 
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 用户id
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "用户id", allowEmptyValue=false)
    private Integer userid;

    /**
     * 操作地址
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作地址", allowEmptyValue=false)
    private String url;

    /**
     * 来源页面
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "来源页面", allowEmptyValue=false)
    private String referer;

    /**
     * 操作来源ip
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作来源ip", allowEmptyValue=false)
    private String ip;

    /**
     * 操作浏览器ua
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作浏览器ua", allowEmptyValue=false)
    private String ua;

    /**
     * 提交的数据
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "提交的数据", allowEmptyValue=true)
    private String postdata;

    /**
     * 创建时间
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:46:14 CST 2020
     * @return
     */
    @ApiModelProperty(value = "创建时间", allowEmptyValue=false)
    private Date cteatetime;

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
     * @return null : 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid : 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return null : 操作地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url : 操作地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return null : 来源页面
     */
    public String getReferer() {
        return referer;
    }

    /**
     * @param referer : 来源页面
     */
    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    /**
     * @return null : 操作来源ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip : 操作来源ip
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
     * @return null : 创建时间
     */
    public Date getCteatetime() {
        return cteatetime;
    }

    /**
     * @param cteatetime : 创建时间
     */
    public void setCteatetime(Date cteatetime) {
        this.cteatetime = cteatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", url=").append(url);
        sb.append(", referer=").append(referer);
        sb.append(", ip=").append(ip);
        sb.append(", ua=").append(ua);
        sb.append(", postdata=").append(postdata);
        sb.append(", cteatetime=").append(cteatetime);
        sb.append("]");
        return sb.toString();
    }
}