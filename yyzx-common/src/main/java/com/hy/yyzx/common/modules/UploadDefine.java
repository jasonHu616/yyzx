package com.hy.yyzx.common.modules;



import com.hy.yyzx.common.utils.FilesizeUtil;

import java.io.Serializable;

/**
 * 上传文件定义
 *
 * @author Aidy
 */
public class UploadDefine implements Serializable {
    private static final long serialVersionUID = 1L;
    private String[] allow;
    private String[] deny;
    private String maxSize;
    private long maxFileSize;
    private String dir;
    private boolean createThumb;
    private int thumbWidth;
    private int thumbHeight;

    public String[] getAllow() {
        return allow;
    }

    public void setAllow(String[] allow) {
        this.allow = allow;
    }

    public String[] getDeny() {
        return deny;
    }

    public void setDeny(String[] deny) {
        this.deny = deny;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxFileSize = FilesizeUtil.sizeToLong(maxSize);
        this.maxSize = maxSize;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public boolean isCreateThumb() {
        return createThumb;
    }

    public void setCreateThumb(boolean createThumb) {
        this.createThumb = createThumb;
    }

    public int getThumbWidth() {
        return thumbWidth;
    }

    public void setThumbWidth(int thumbWidth) {
        this.thumbWidth = thumbWidth;
    }

    public int getThumbHeight() {
        return thumbHeight;
    }

    public void setThumbHeight(int thumbHeight) {
        this.thumbHeight = thumbHeight;
    }
}
