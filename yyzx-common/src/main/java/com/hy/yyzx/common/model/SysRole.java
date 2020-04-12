/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户角色
 * sys_role
 * @date Sun Apr 12 20:01:24 CST 2020
 *
 */
@ApiModel(value = "SysRole", description = "系统用户角色")
@SuppressWarnings("serial")
public class SysRole implements Serializable {
    /**
     * 

     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:24 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 角色名称
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:24 CST 2020
     * @return
     */
    @ApiModelProperty(value = "角色名称", allowEmptyValue=false)
    private String title;

    /**
     * 排序码
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:24 CST 2020
     * @return
     */
    @ApiModelProperty(value = "排序码", allowEmptyValue=false)
    private Integer ordernum;

    /**
     * 创建时间
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 20:01:24 CST 2020
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
     * @return null : 角色名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title : 角色名称
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return null : 排序码
     */
    public Integer getOrdernum() {
        return ordernum;
    }

    /**
     * @param ordernum : 排序码
     */
    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
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
        sb.append(", title=").append(title);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}