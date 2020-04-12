/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统管理员
 * sys_user
 * @date Sun Apr 12 18:28:02 CST 2020
 *
 */
@ApiModel(value = "SysUser", description = "系统管理员")
@SuppressWarnings("serial")
public class SysUser implements Serializable {
    /**
     * id自增列
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 电话
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "电话", allowEmptyValue=false)
    private String phone;

    /**
     * 帐号
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "帐号", allowEmptyValue=false)
    private String username;

    /**
     * 真实姓名
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "真实姓名", allowEmptyValue=false)
    private String realname;

    /**
     * 密码md5加密
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "密码md5加密", allowEmptyValue=false)
    private String pwd;

    /**
     * 管理员类型0-普通管理员，1-超级管理员,2-开发管理员

     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "管理员类型0-普通管理员，1-超级管理员,2-开发管理员", allowEmptyValue=false)
    private Integer admintype;

    /**
     * 是否冻结禁用0-正常，1-禁用
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "是否冻结禁用0-正常，1-禁用", allowEmptyValue=false)
    private Integer isfreeze;

    /**
     * 是否删除0-正常，1-删除
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "是否删除0-正常，1-删除", allowEmptyValue=false)
    private Integer isdelete;

    /**
     * 创建人
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "创建人", allowEmptyValue=true)
    private Integer createby;

    /**
     * 创建时间
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "创建时间", allowEmptyValue=false)
    private Date createtime;

    /**
     * 最后一次修改人
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "最后一次修改人", allowEmptyValue=true)
    private Integer updateby;

    /**
     * 最后一次修改时间
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "最后一次修改时间", allowEmptyValue=true)
    private Date updatetime;

    /**
     * 登录错误次数
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "登录错误次数", allowEmptyValue=false)
    private Integer errorcount;

    /**
     * 最后一次登录错误时间
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "最后一次登录错误时间", allowEmptyValue=true)
    private Date errortime;

    /**
     * 是否因登录错误锁定0-否,1-是
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "是否因登录错误锁定0-否,1-是", allowEmptyValue=false)
    private Integer isclose;

    /**
     * 用户角色id（sys_role->id）
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 18:28:02 CST 2020
     * @return
     */
    @ApiModelProperty(value = "用户角色id（sys_role->id）", allowEmptyValue=false)
    private Integer roleid;

    /**
     * @return null : id自增列
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : id自增列
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return null : 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone : 电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return null : 帐号
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username : 帐号
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return null : 真实姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname : 真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * @return null : 密码md5加密
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd : 密码md5加密
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * @return null : 管理员类型0-普通管理员，1-超级管理员,2-开发管理员

     */
    public Integer getAdmintype() {
        return admintype;
    }

    /**
     * @param admintype : 管理员类型0-普通管理员，1-超级管理员,2-开发管理员

     */
    public void setAdmintype(Integer admintype) {
        this.admintype = admintype;
    }

    /**
     * @return null : 是否冻结禁用0-正常，1-禁用
     */
    public Integer getIsfreeze() {
        return isfreeze;
    }

    /**
     * @param isfreeze : 是否冻结禁用0-正常，1-禁用
     */
    public void setIsfreeze(Integer isfreeze) {
        this.isfreeze = isfreeze;
    }

    /**
     * @return null : 是否删除0-正常，1-删除
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * @param isdelete : 是否删除0-正常，1-删除
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * @return null : 创建人
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * @param createby : 创建人
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
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

    /**
     * @return null : 最后一次修改人
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * @param updateby : 最后一次修改人
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    /**
     * @return null : 最后一次修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime : 最后一次修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return null : 登录错误次数
     */
    public Integer getErrorcount() {
        return errorcount;
    }

    /**
     * @param errorcount : 登录错误次数
     */
    public void setErrorcount(Integer errorcount) {
        this.errorcount = errorcount;
    }

    /**
     * @return null : 最后一次登录错误时间
     */
    public Date getErrortime() {
        return errortime;
    }

    /**
     * @param errortime : 最后一次登录错误时间
     */
    public void setErrortime(Date errortime) {
        this.errortime = errortime;
    }

    /**
     * @return null : 是否因登录错误锁定0-否,1-是
     */
    public Integer getIsclose() {
        return isclose;
    }

    /**
     * @param isclose : 是否因登录错误锁定0-否,1-是
     */
    public void setIsclose(Integer isclose) {
        this.isclose = isclose;
    }

    /**
     * @return null : 用户角色id（sys_role->id）
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid : 用户角色id（sys_role->id）
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", username=").append(username);
        sb.append(", realname=").append(realname);
        sb.append(", pwd=").append(pwd);
        sb.append(", admintype=").append(admintype);
        sb.append(", isfreeze=").append(isfreeze);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", createby=").append(createby);
        sb.append(", createtime=").append(createtime);
        sb.append(", updateby=").append(updateby);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", errorcount=").append(errorcount);
        sb.append(", errortime=").append(errortime);
        sb.append(", isclose=").append(isclose);
        sb.append(", roleid=").append(roleid);
        sb.append("]");
        return sb.toString();
    }
}