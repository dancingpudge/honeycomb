package com.honeycomb.demo2.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: hoenycomb-parent
 * @description: demo 展示
 * @author: wangzz
 * @create: 2020-12-23 19:13
 */
@Data
public class DemoAccountVO {
    /**
     * 主键
     */
    private Integer accountId;
    /**
     * 账户编码
     */
    private String accountCode;

    /**
     * 立方用户标识
     */
    private String unionId;

    private String userName;

    /**
     * 开户状态
     */
    private String accountStatus;

    /**
     * 终端
     */
    private String terminal;

    /**
     * 是否充值过
     */
    private Integer isRecharge;

    /**
     * 无感支付状态1开0未开
     */
    private Boolean unconsciousStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 开户时间
     */
    private LocalDateTime activeTime;
}
