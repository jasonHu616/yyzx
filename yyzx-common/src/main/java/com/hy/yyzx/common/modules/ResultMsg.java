package com.hy.yyzx.common.modules;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aidy 2018/2/28 0028 10:24
 */
public class ResultMsg implements Serializable {
    private static final long serialVersionUID = 163668651892643175L;
    private boolean success;
    private String message;
    private Integer status;
    private Object data;

    public ResultMsg() {
    }

    public ResultMsg(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultMsg(boolean success, String message, Object data,Integer status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", success);
        map.put("message", message);
        if (data != null) map.put("data", data);
        return map;
    }
}
