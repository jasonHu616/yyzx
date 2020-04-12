package com.hy.yyzx.common.modules;

import java.io.Serializable;

public class PageModel implements Serializable {

    private int size=20;
    private int page=1;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
