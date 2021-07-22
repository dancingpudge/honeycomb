package com.honeycomb.dingtalk;

/**
 * 钉钉消息结果
 *
 * @author guanym
 */
public class DingtalkResult {

    // {"errmsg":"ok","errcode":0}
    /**
     * 消息（ok）
     */
    private String errmsg;
    /**
     * 状态（0表示成功）
     */
    private String errcode;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

}
