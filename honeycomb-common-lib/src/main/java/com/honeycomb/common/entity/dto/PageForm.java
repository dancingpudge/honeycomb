package com.honeycomb.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @PackgeName: com.honeycomb.common.entity.form
 * @Description: 分页查询对象
 * @Author: LiuH
 * @Date 2020-12-23 13:33:13
 **/
public class PageForm {
    @ApiModelProperty("当前页码 1开始")
    @JsonProperty("cur_page")
    private Integer curPage;

    @ApiModelProperty("每页数量默认15")
    @JsonProperty("page_size")
    private Integer pageSize;

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
