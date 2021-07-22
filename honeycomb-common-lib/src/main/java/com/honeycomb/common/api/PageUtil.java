package com.honeycomb.common.api;

import java.io.Serializable;

/**
 * 2020-12-23 edit by hnn 添加泛型data，方便vo获取
 *
 * @author: zhoupeng
 * @description:分页工具
 * @create: 2019-11-04 14:00
 **/
public class PageUtil implements Serializable {
    /**
     * 当前页
     */
    private Long curPage = 1L;
    /**
     * 页面大小
     */
    private Long pageSize = 15L;
    /**
     * 总记录数
     */
    private Long totalRows = 0L;
    /**
     * 总页数
     */
    private Long pageCount = 0L;

    /**
     *
     */
    public PageUtil() {
        super();
    }

    /**
     * @param curPage  当前页
     * @param pageSize 页面大小
     */
    public PageUtil(Long curPage, Long pageSize) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    /**
     * @param curPage   当前页
     * @param pageSize  页面大小
     * @param totalRows 总数
     */
    public PageUtil(Long curPage, Long pageSize, Long totalRows) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalRows = totalRows;
        getPageCount();
    }

    /**
     * @param curPage  当前页
     * @param pageSize 页面大小
     */
    public PageUtil(Integer curPage, Integer pageSize) {
        super();
        this.curPage = Long.valueOf(curPage);
        this.pageSize = Long.valueOf(pageSize);
    }

    /**
     * @param curPage   当前页
     * @param pageSize  页面大小
     * @param totalRows 总数
     */
    public PageUtil(Integer curPage, Integer pageSize, Long totalRows) {
        super();
        this.curPage = Long.valueOf(curPage);
        this.pageSize = Long.valueOf(pageSize);
        this.totalRows = Long.valueOf(totalRows);
        getPageCount();
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
        getPageCount();
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = Long.valueOf(totalRows);
        getPageCount();
    }


    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }


    /**
     * 总页数
     *
     * @return
     */
    public Long getPageCount() {
        this.pageCount = ((getTotalRows() == null ? 0 : getTotalRows()) + this.getPageSize() - 1)
                / this.getPageSize();
        return this.pageCount;
    }
}

