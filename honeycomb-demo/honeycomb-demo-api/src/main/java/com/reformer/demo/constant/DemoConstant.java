package com.honeycomb.demo.constant;

/**
 * @program: honeycomb-parent
 * @description: 参数校验样例
 * @author: LiuH
 * @create: 2020-12-02 14:55
 */
public class DemoConstant {

    /**
     * 电子钱包回调类型
     */
    public static final String CALL_BACK_OPEN_ACCOUNT = "CALL_BACK_OPEN_ACCOUNT";
    public static final String CALL_BACK_RECHARGE = "CALL_BACK_RECHARGE";
    public static final String CALL_BACK_PAY_SIGN = "CALL_BACK_PAY_SIGN";
    public static final String CALL_BACK_UN_SIGN = "CALL_BACK_UN_SIGN";
    public static final String CALL_BACK_COLLECT_SIGN = "CALL_BACK_COLLECT_SIGN";
    public static final String CALL_BACK_COLLECT_UN_SIGN = "CALL_BACK_COLLECT_UN_SIGN";


    public static final String CALL_BACK_REG = CALL_BACK_OPEN_ACCOUNT + "|" + CALL_BACK_RECHARGE + "|"
            + CALL_BACK_PAY_SIGN + "|" + CALL_BACK_UN_SIGN + "|"
            + CALL_BACK_COLLECT_SIGN + "|" + CALL_BACK_COLLECT_UN_SIGN;

}
