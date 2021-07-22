package com.honeycomb.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @PackgeName: com.honeycomb.common.entity.form
 * @Description: 分页查询对象
 * @Author: LiuH
 * @Date 2020-12-23 13:33:13
 **/
public class PageTimeRangeForm {
    @ApiModelProperty("开始时间 yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_datetime")
    protected String startDatetime;

    @ApiModelProperty("结束时间 yyyy-MM-dd HH:mm:ss")
    @JsonProperty("end_datetime")
    protected String endDatetime;

    @ApiModelProperty("当前页码 1开始")
    @JsonProperty("cur_page")
    private Integer curPage;

    @ApiModelProperty("每页数量默认15")
    @JsonProperty("page_size")
    private Integer pageSize;

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
