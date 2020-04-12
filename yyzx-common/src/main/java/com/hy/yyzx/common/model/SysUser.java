/*** copyright (c) 2020 Jason  ***/
package com.hy.yyzx.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * sys_user
 * @date Fri Apr 10 11:31:19 CST 2020
 *
 */
@ApiModel(value = "SysUser", description = "")
@SuppressWarnings("serial")
public class SysUser implements Serializable {
    /**
     * id自增键
     * NOT NULL
     * IS AUTO_INCREMENT
     * @author Jason
     * @date Fri Apr 10 11:31:19 CST 2020
     * @return
     */
    @ApiModelProperty(value = "ID 自增序列", allowEmptyValue=false)
    private Integer id;

    /**
     * 用户名
     * NOT NULL
     * NOT AUTO_INCREMENT
     * @author Jason
     * @date Fri Apr 10 11:31:19 CST 2020
     * @return
     */
    @ApiModelProperty(value = "用户名", allowEmptyValue=false)
    private String username;

    /**
     * @return null : id自增键
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id : id自增键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return null : 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username : 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append("]");
        return sb.toString();
    }
}