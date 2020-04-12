/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 用户角色权限
 * sys_role_permission
 * @date Sun Apr 12 20:01:53 CST 2020
 *
 */
@ApiModel(value = "SysRolePermission", description = "用户角色权限")
@SuppressWarnings("serial")
public class SysRolePermission implements Serializable {
    /**
     * 
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 角色id（sys_role-->id） 
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "角色id（sys_role-->id） ", allowEmptyValue=false)
    private Integer roleid;

    /**
     * 菜单key
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "菜单key", allowEmptyValue=false)
    private String menukey;

    /**
     * 操作名称(如add,edit,list等，与客户端定义匹配)
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作名称(如add,edit,list等，与客户端定义匹配)", allowEmptyValue=false)
    private String actions;

    /**
     * 操作权限范围(1:医院 2:所有)
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "操作权限范围(1:医院 2:所有)", allowEmptyValue=false)
    private Integer datascope;

    /**
     * 医院id(1,2,3)datascope =1 时必填 
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:53 CST 2020
     * @return
     */
    @ApiModelProperty(value = "医院id(1,2,3)datascope =1 时必填 ", allowEmptyValue=true)
    private String hospitalids;

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
     * @return null : 角色id（sys_role-->id） 
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * @param roleid : 角色id（sys_role-->id） 
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * @return null : 菜单key
     */
    public String getMenukey() {
        return menukey;
    }

    /**
     * @param menukey : 菜单key
     */
    public void setMenukey(String menukey) {
        this.menukey = menukey == null ? null : menukey.trim();
    }

    /**
     * @return null : 操作名称(如add,edit,list等，与客户端定义匹配)
     */
    public String getActions() {
        return actions;
    }

    /**
     * @param actions : 操作名称(如add,edit,list等，与客户端定义匹配)
     */
    public void setActions(String actions) {
        this.actions = actions == null ? null : actions.trim();
    }

    /**
     * @return null : 操作权限范围(1:医院 2:所有)
     */
    public Integer getDatascope() {
        return datascope;
    }

    /**
     * @param datascope : 操作权限范围(1:医院 2:所有)
     */
    public void setDatascope(Integer datascope) {
        this.datascope = datascope;
    }

    /**
     * @return null : 医院id(1,2,3)datascope =1 时必填 
     */
    public String getHospitalids() {
        return hospitalids;
    }

    /**
     * @param hospitalids : 医院id(1,2,3)datascope =1 时必填 
     */
    public void setHospitalids(String hospitalids) {
        this.hospitalids = hospitalids == null ? null : hospitalids.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleid=").append(roleid);
        sb.append(", menukey=").append(menukey);
        sb.append(", actions=").append(actions);
        sb.append(", datascope=").append(datascope);
        sb.append(", hospitalids=").append(hospitalids);
        sb.append("]");
        return sb.toString();
    }
}