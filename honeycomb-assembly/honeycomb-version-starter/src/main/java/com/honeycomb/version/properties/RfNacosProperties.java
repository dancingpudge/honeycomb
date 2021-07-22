package com.honeycomb.version.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @description: com.honeycomb.version.properties <br>
 * @date: 2021/4/9 10:05 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Data
@Component
@RefreshScope
@Profile({"dev", "test", "prod"})
public class RfNacosProperties {

    @Value("${spring.cloud.nacos.config.server-addr}")
    private String serverAddr;

    @Value("${spring.cloud.nacos.config.username}")
    private String username;

    @Value("${spring.cloud.nacos.config.password}")
    private String password;

    @Value("${spring.cloud.nacos.config.shared-configs[0].data-id}")
    private String dataId;

    @Value("${spring.cloud.nacos.config.shared-configs[0].group}")
    private String group;

    @Value("${spring.cloud.nacos.config.namespace}")
    private String namespace;
}