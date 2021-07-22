package com.honeycomb.demo2.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: hoenycomb-parent
 * @description: demo新增对象
 * @author: wangzz
 * @create: 2020-12-23 19:03
 */
@Data
public class DemoAccountDTO {

    @ApiModelProperty("用户名")
    @JsonProperty("user_name")
    private String userName;

    @ApiModelProperty("账号编码")
    @JsonProperty("account_code")
    private String accountCode;
}
