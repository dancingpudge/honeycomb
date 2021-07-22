package com.honeycomb.common.feign.entity;

/**
 * @description: com.honeycomb.common.entity.dto <br>
 * @date: 2021/1/18 7:53 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public class ReformerRetryInfo {
    private String clzName;
    private String methodName;
    private String data;
    private Class paramClz;

    public ReformerRetryInfo(String clzName, String methodName, String data,Class paramClz) {
        this.clzName = clzName;
        this.methodName = methodName;
        this.data = data;
        this.paramClz = paramClz;
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Class getParamClz() {
        return paramClz;
    }

    public void setParamClz(Class paramClz) {
        this.paramClz = paramClz;
    }
}
