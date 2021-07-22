package com.honeycomb.common.config;

import com.honeycomb.common.annotation.EnableBusinessLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: honeycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-03 17:25
 **/
@Configuration
@ConditionalOnBean(annotation = EnableBusinessLog.class)
@ConfigurationProperties(prefix = "honeycomb.log")
public class BusinessLogConfig {
    private String sourceSystem;
    private String topic;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
