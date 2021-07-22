package com.honeycomb.log.config;

import com.honeycomb.log.ReformerHttpLogAspect;
import com.honeycomb.log.annotation.EnableBusinessLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: com.honeycomb.log <br>
 * @date: 2021/2/20 2:30 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Configuration
@ConditionalOnBean(annotation = EnableBusinessLog.class)
@EnableConfigurationProperties(ReformerLogProperties.class)
public class ReformerLogConfig {

    @Bean
    ReformerHttpLogAspect honeycombHttpLogAspect() {
        return new ReformerHttpLogAspect();
    }
}
