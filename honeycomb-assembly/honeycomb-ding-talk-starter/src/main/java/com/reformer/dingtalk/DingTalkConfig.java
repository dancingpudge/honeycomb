package com.honeycomb.dingtalk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * @Description 钉钉机器人配置
 * @Author LiuH
 * @Date 2020/11/24
 */
@Configuration
@ConditionalOnProperty(value = "dingtalk.open")
@EnableConfigurationProperties(DingTalkProperties.class)
@Import({RestTemplateConfig.class})
public class DingTalkConfig {

    @Bean
    DingTalkClient dingTalkClient(DingTalkProperties dingTalkProperties, RestTemplate restTemplate){
        return new DingTalkClient(dingTalkProperties, restTemplate);
    }
}
