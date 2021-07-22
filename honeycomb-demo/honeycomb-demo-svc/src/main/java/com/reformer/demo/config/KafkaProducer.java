package com.honeycomb.demo.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @program: hoenycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-10 19:48
 **/
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 处理结果异步回调，需要业务系统自行实现，如果不实现则不处理
     *
     * @param kafkaMsg
     * @param topic
     */
    public void doProduce(JSONObject kafkaMsg, String topic) {
        kafkaTemplate.send(topic, JSONObject.toJSONString(kafkaMsg))
                .addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.debug("发送消息失败：" + ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Object> result) {
                        log.debug("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                                + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
                    }
                });
    }


}
