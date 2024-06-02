package com.haole.logistics.r2dbc.common;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * ClassName: PagedResultVO
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/31 15:36
 */
public class PagedResultVO<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7875288914812655355L;
    private int currentPage;
    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> records;
    private boolean isFirstPage = false;
    private boolean isLastPage = false;
    private int pages;
    private Object extraData;

    private PagedResultVO() {
    }

    public PagedResultVO(List<T> list, int pageNum, int pageSize, long total) {
        this.records = list;
        this.total = total;
        this.currentPage = pageNum;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = (int) Math.ceil((double) total / (double) pageSize);
        this.judgePageBoudary();
    }

    private void judgePageBoudary() {
        this.isFirstPage = this.currentPage == 1;
        this.isLastPage = this.currentPage == this.pages || this.pages == 0;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public boolean isFirstPage() {
        return this.isFirstPage;
    }

    public void setFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isLastPage() {
        return this.isLastPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Object getExtraData() {
        return this.extraData;
    }

    public void setExtraData(Object extraData) {
        this.extraData = extraData;
    }
}
