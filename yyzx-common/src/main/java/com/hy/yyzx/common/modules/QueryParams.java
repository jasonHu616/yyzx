package com.hy.yyzx.common.modules;

import java.io.Serializable;

/**
 * 查询条件对象
 *
 * @author Aidy 2018/4/28 0028 12:21
 */
public class QueryParams implements Serializable {
    private static final long serialVersionUID = -365594228136191582L;
    private String condition;
    private Object[] params;

    public QueryParams() {
    }

    public QueryParams(String condition, Object[] params) {
        this.condition = condition;
        this.params = params;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
