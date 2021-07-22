package com.honeycomb.common.log;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: honeycomb-parent
 * @description: 业务日志模板
 * @author: LiuH
 * @create: 2020-12-03 17:15
 **/
public class BusinessLogVO {
    private String topic;
    @JsonProperty("msgtype")
    private String msgType;
    private String carParkId;
    @JsonProperty("message")
    private Message message;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(String carParkId) {
        this.carParkId = carParkId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "topic: " + topic + " msgType: " + msgType + " carParkId:" + carParkId
                + " message: {sourceSystem" + message.toString() + "}";
    }
}
