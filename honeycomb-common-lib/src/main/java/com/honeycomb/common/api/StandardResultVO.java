package com.honeycomb.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 标准格式返回页面结果
 *
 * @author Zongjie.Xia
 */
public class StandardResultVO<T> implements Serializable {

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String LOGIC_ERR_CODE = "555";

    @Deprecated
    public static <T> StandardResultVO<T> errorResult() {
        return errorResult("操作失败");
    }

    @Deprecated
    public static <T> StandardResultVO<T> errorResult(String message) {
        return errorResult(LOGIC_ERR_CODE, message);
    }

    public static <T> StandardResultVO<T> errorResult(String errorCode, String message) {
        return new StandardResultVO<T>(errorCode, message, null);
    }

    public static StandardResultVO hystrixResult() {
        return new StandardResultVO("556", "", null);
    }

    public static <T> StandardResultVO<T> successResult() {
        return successResult(null);
    }

    public static <T> StandardResultVO<T> successResult(T data) {
        return successResult(data, SUCCESS_MESSAGE);
    }

    @Deprecated
    public static <T> StandardResultVO<T> successResult(T data, String message) {
        return new StandardResultVO<T>(SUCCESS_CODE, message, data);
    }

    public StandardResultVO() {
        super();
    }

    public StandardResultVO(String errorCode, String resultMsg, T data) {
        super();
        this.resultCode = errorCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    /**
     * 返回结果信息
     */

    private T data;
    /**
     * 返回code
     */
    @JsonProperty("result_code")
    private String resultCode;

    /**
     * 返回信息
     */
    @JsonProperty("result_msg")
    private String resultMsg;

    public boolean success() {
        return this.resultCode.equalsIgnoreCase(SUCCESS_CODE);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
