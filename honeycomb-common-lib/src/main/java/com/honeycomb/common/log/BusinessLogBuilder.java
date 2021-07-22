package com.honeycomb.common.log;

import com.alibaba.fastjson.JSON;
import com.honeycomb.common.config.BusinessLogConfig;
import com.honeycomb.common.util.DateTimeUtil;

/**
 * @program: honeycomb-parent
 * @description: 日志对象生成器
 * @author: LiuH
 * @create: 2020-12-04 09:14
 **/
public class BusinessLogBuilder {
    BusinessLogVO businessLogVO;
    BusinessLogConfig config;

    public BusinessLogBuilder(BusinessLogConfig businessLogConfig, String carParkId, String msgType) {
        this.config = businessLogConfig;
        BusinessLogVO businessLogVO = new BusinessLogVO();
        businessLogVO.setTopic(businessLogConfig.getTopic());
        businessLogVO.setCarParkId(carParkId);
        businessLogVO.setMsgType(msgType);
        this.businessLogVO = businessLogVO;
    }

    public BusinessLogVO build() {
        return businessLogVO;
    }

    /**
     * 生成消息体
     *
     * @param operateContent
     * @return
     */
    public BusinessLogBuilder setMessage(String operateContent) {
        Message message = new Message();
        this.businessLogVO.setMessage(message);
        message.setSourceSystem(config.getSourceSystem());
        message.setOperateContent(operateContent);
        message.setBusinessTopic("0");
        message.setEventTime(DateTimeUtil.getDateTimeDisplayString());
        return this;
    }

    /**
     * 添加自定义参数
     *
     * @param businessMsg
     * @return
     */
    public BusinessLogBuilder setBusinessMsg(String businessMsg) {
        this.businessLogVO.getMessage().setBusinessMsg(JSON.toJSONString(businessMsg));
        return this;
    }

    public BusinessLogBuilder setSystemModule(String systemModule) {
        this.businessLogVO.getMessage().setSystemModule(systemModule);
        return this;
    }


    public BusinessLogBuilder setBusinessTopic(String businessTopic) {
        this.businessLogVO.getMessage().setBusinessTopic(businessTopic);
        return this;
    }
}
