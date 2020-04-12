/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 系统配置项
 * sys_config
 * @date Sun Apr 12 19:19:09 CST 2020
 *
 */
@ApiModel(value = "SysConfig", description = "系统配置项")
@SuppressWarnings("serial")
public class SysConfig implements Serializable {
    /**
     * 
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 编码
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "编码", allowEmptyValue=false)
    private String keycode;

    /**
     * 编辑类型(input/text/radio/checkbox/select/switch)
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "编辑类型(input/text/radio/checkbox/select/switch)", allowEmptyValue=false)
    private String typecode;

    /**
     * 标题
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "标题", allowEmptyValue=false)
    private String title;

    /**
     * 配置分组id（sys_config_group-->id）
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "配置分组id（sys_config_group-->id）", allowEmptyValue=false)
    private Integer groupid;

    /**
     * 配置选项(针对radio/checkbox/select/switch)val:text;val:text
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "配置选项(针对radio/checkbox/select/switch)val:text;val:text", allowEmptyValue=true)
    private String options;

    /**
     * 配置项说明
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "配置项说明", allowEmptyValue=true)
    private String remark;

    /**
     * 配置项值
     * CAN BE NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "配置项值", allowEmptyValue=true)
    private String value;

    /**
     * 排序码
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Sun Apr 12 19:19:09 CST 2020
     * @return
     */
    @ApiModelProperty(value = "排序码", allowEmptyValue=false)
    private Integer ordernum;

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
     * @return null : 编码
     */
    public String getKeycode() {
        return keycode;
    }

    /**
     * @param keycode : 编码
     */
    public void setKeycode(String keycode) {
        this.keycode = keycode == null ? null : keycode.trim();
    }

    /**
     * @return null : 编辑类型(input/text/radio/checkbox/select/switch)
     */
    public String getTypecode() {
        return typecode;
    }

    /**
     * @param typecode : 编辑类型(input/text/radio/checkbox/select/switch)
     */
    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    /**
     * @return null : 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title : 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return null : 配置分组id（sys_config_group-->id）
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * @param groupid : 配置分组id（sys_config_group-->id）
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * @return null : 配置选项(针对radio/checkbox/select/switch)val:text;val:text
     */
    public String getOptions() {
        return options;
    }

    /**
     * @param options : 配置选项(针对radio/checkbox/select/switch)val:text;val:text
     */
    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    /**
     * @return null : 配置项说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark : 配置项说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return null : 配置项值
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value : 配置项值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", keycode=").append(keycode);
        sb.append(", typecode=").append(typecode);
        sb.append(", title=").append(title);
        sb.append(", groupid=").append(groupid);
        sb.append(", options=").append(options);
        sb.append(", remark=").append(remark);
        sb.append(", value=").append(value);
        sb.append(", ordernum=").append(ordernum);
        sb.append("]");
        return sb.toString();
    }
}