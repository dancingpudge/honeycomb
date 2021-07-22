package com.honeycomb.common.log;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: honeycomb-parent
 * @description: 日志详情
 * @author: LiuH
 * @create: 2020-12-03 19:16
 **/
public class Message {
    /**
     * 来源系统
     */
    @JsonProperty("source_system")
    private String sourceSystem;
    /**
     * 系统模块
     */
    @JsonProperty("system_module")
    private String systemModule;
    /**
     * 系统模块编码
     */
    @JsonProperty("bussiness_topic")
    private String businessTopic;
    @JsonProperty("event_time")
    private String eventTime;
    @JsonProperty("operate_content")
    private String operateContent;
    @JsonProperty("business_msg")
    private String businessMsg;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSystemModule() {
        return systemModule;
    }

    public void setSystemModule(String systemModule) {
        this.systemModule = systemModule;
    }

    public String getBusinessTopic() {
        return businessTopic;
    }

    public void setBusinessTopic(String businessTopic) {
        this.businessTopic = businessTopic;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getOperateContent() {
        return operateContent;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getBusinessMsg() {
        return businessMsg;
    }

    public void setBusinessMsg(String businessMsg) {
        this.businessMsg = businessMsg;
    }

    @Override
    public String toString() {
        return "sourceSystem: " + sourceSystem + " systemModule: " + systemModule + " businessTopic:" + businessTopic
                + " eventTime:" + eventTime + " operateContent:" + operateContent + " businessMsg: " + businessMsg;
    }
}
