package com.hy.yyzx.common.modules;

/**
 * @author Aidy 2018/4/2 0002 17:48
 */

import java.io.Serializable;


/**
 * 上传文件返回体
 *
 * @author Aidy
 */
public class UploadResult extends ResultMsg implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer dataid;    //数据ID
    private String title;    //文件标题
    private String fileName;    //文件原始名称
    private String filePath;    //文件访问相对路径
    private long fileSize;        //文件大小(b)
    private String fileExt;        //文件扩展名(含.)

    /**
     * 转换到ResponseData
     *
     * @return
     */
    public ResultData<UploadResult> toResponseData() {
        return ResultData.create()
                .success(isSuccess())
                .status(isSuccess() ? ResponseStatus.SUCCESS : ResponseStatus.ERROR)
                .message(getMessage())
                .result(this);
    }

    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }
}
