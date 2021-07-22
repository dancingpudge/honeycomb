package com.honeycomb.demo.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * @program: honeycomb-parent
 * @description: 数据库实体类
 * @author: LiuH
 * @create: 2020-12-03 14:00
 **/
@Data
public class Demo {
    private Integer id;

    private String callBackType;

    private String confirmTime;

    private String username;

    private String phone;

    private Date createTime;

    private Boolean success;
}
