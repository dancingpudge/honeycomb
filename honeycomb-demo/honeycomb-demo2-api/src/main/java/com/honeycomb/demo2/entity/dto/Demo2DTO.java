package com.honeycomb.demo2.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @program: honeycomb-parent
 * @description: 参数校验样例
 * http://hibernate.org/validator/documentation/
 * spring-boot-starter-web包里面有hibernate-validator包，不需要引用hibernate validator依赖。
 * 2.4.0版本后移除需要单独引用
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
@Data
public class Demo2DTO {


    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    @ApiModelProperty("时间")
    @Future(message = "时间必须是将来时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("是否成功")
    @JsonProperty("is_success")
    private Boolean isSuccess;
}
