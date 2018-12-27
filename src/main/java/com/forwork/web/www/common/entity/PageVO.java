package com.forwork.web.www.common.entity;

import java.util.List;

public class PageVO {
    private int rowStart = 1;
    private int pagePerRow = 10;
    private int total;
    private List list;

    public PageVO() {
    }

    public PageVO(int rowStart, int pagePerRow, int total, List list) {
        this.rowStart = rowStart;
        this.pagePerRow = pagePerRow;
        this.total = total;
        this.list = list;
    }

    public int getRowStart() {
        return this.rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getPagePerRow() {
        return this.pagePerRow;
    }

    public void setPagePerRow(int pagePerRow) {
        this.pagePerRow = pagePerRow;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }
}

