package com.hy.yyzx.common.modules;

import java.io.Serializable;

/**
 * @author Aidy 2018/3/20 0020 14:34
 */
public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = -3103588919084285080L;
    private boolean success;    //是否成功
    private int status;  //状态
    private String message; //消息
    private T result;    //返回消息内容

    /**
     * 创建默认ResponseData对象
     *
     * @return
     */
    public static ResultData create() {
        return new ResultData();
    }

    /**
     * 创建完整定义的ResponseData对象
     *
     * @param success
     * @param status
     * @param message
     * @param data
     * @return
     */
    public static ResultData create(boolean success, int status, String message, Object data) {
        return new ResultData(status == 0, status, message, data);
    }

    /**
     * 创建完整定义的ResponseData对象
     *
     * @param status
     * @param message
     * @param data
     * @return
     */
    public static ResultData create(int status, String message, Object data) {
        return new ResultData(status == 0, status, message, data);
    }

    /**
     * 创建带状态及Message的ResponseData对象
     *
     * @return
     */
    public static ResultData create(int status, String message) {
        return new ResultData(status == 0, status, message, null);
    }

    /**
     * 创建成功的ResponseData对象
     *
     * @return
     */
    public static ResultData success(String message, Object data) {
        return create(ResponseStatus.SUCCESS, message, data);
    }

    /**
     * 创建失败的ResponseData对象
     *
     * @return
     */
    public static ResultData failed(String message) {
        return create(ResponseStatus.ERROR, message,new MapEntity());
    }

    /**
     * 无权限
     *
     * @return
     */
    public static ResultData noauth() {
        return create(ResponseStatus.AUTHFAILD, "AUTHFAILD");
    }


    private ResultData() {

    }

    private ResultData(boolean success, int status, String message, T result) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.result = result;
    }


    public ResultData<T> success(boolean success) {
        this.success = success;
        return this;
    }

    public ResultData<T> status(int status) {
        this.status = status;
        return this;
    }

    public ResultData<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResultData<T> result(T result) {
        this.result = result;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
