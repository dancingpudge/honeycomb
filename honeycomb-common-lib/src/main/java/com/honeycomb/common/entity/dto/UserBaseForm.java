package com.honeycomb.common.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * @PackgeName: com.honeycomb.common.entity.form
 * @Description: 用户信息查询对象
 * @Author: LiuH
 * @Date 2020-12-23 13:33:13
 **/
public class UserBaseForm {
    /**
     * 立方用户id
     */
    @ApiModelProperty("用户标识")
    @JsonProperty("union_id")
    protected String unionId;

    /**
     * 立方用户名称
     */
    @ApiModelProperty("用户名")
    @JsonProperty("user_name")
    protected String userName;

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
