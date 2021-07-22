package com.honeycomb.demo2.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 钱包账户表
 * </p>
 *
 * @author demo
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DemoAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户号
     */
    @TableId(value = "account_id", type = IdType.AUTO)
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

    /**
     * 是否删除
     */
    private Boolean isDelete;


}
