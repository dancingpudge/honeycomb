package com.honeycomb.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 标准分页格式返回
 *
 * @author: zhoupeng
 * @description:
 * @create: 2019-11-04 14:28
 **/
public class StandardPageResultVO<T> extends StandardResultVO<T> {

    /**
     * 当前页
     */
    @JsonProperty("cur_page")
    private Long curPage = 1L;
    /**
     * 页面大小
     */
    @JsonProperty("page_size")
    private Long pageSize = 15L;
    /**
     * 总记录数
     */
    @JsonProperty("total_rows")
    private Long totalRows = 0L;
    /**
     * 总页数
     */
    @JsonProperty("page_count")
    private Long pageCount = 0L;

    public StandardPageResultVO() {
        super();
    }

    public StandardPageResultVO(String errorCode, String resultMsg) {
        this(errorCode, resultMsg, new PageUtil(), null);
    }

    public StandardPageResultVO(String errorCode, String resultMsg, PageUtil pageInfo, T t) {
        super.setResultCode(errorCode);
        super.setResultMsg(resultMsg);
        this.curPage = pageInfo.getCurPage();
        this.pageSize = pageInfo.getPageSize();
        this.totalRows = pageInfo.getTotalRows();
        this.pageCount = pageInfo.getPageCount();
        this.setData(t);
    }

    @Deprecated
    public static <T> StandardPageResultVO<T> errorResult(String message) {
        return new StandardPageResultVO<T>(LOGIC_ERR_CODE, message);
    }

    @Deprecated
    public static <T> StandardPageResultVO<T> errorResult(String errorCode, String message) {
        return new StandardPageResultVO<T>(errorCode, message);
    }

    public static <T> StandardPageResultVO<T> successResult(PageUtil pageInfo, T t) {
        return successResult(pageInfo, t, SUCCESS_MESSAGE);
    }

    @Deprecated
    public static <T> StandardPageResultVO<T> successResult(PageUtil pageInfo, T t, String message) {
        return new StandardPageResultVO<T>(SUCCESS_CODE, message, pageInfo, t);
    }

    @Override
    public String toString() {
        return super.toString();
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
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }
}