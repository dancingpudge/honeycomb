package com.honeycomb.demo.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: honeycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-09 13:18
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoAccountVO {
    private Integer accountId;
    private String accountCode;
    private String unionId;
    private String userName;
    private String accountStatus;
    private String terminal;
    private Integer isRecharge;
    private Boolean unconsciousStatus;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activeTime;
    private Boolean isDelete;
}
