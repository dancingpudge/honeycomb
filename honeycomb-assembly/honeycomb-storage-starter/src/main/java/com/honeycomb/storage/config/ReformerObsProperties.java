package com.honeycomb.storage.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: com.honeycomb.log <br>
 * @date: 2021/2/20 2:31 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Data
@ConfigurationProperties(prefix = "honeycomb.storage.obs")
public class ReformerObsProperties {
    private String accessKey;

    private String secretKey;

    private String endPoint;

    private String bucketName;

    private Integer imageExpire;

    private Integer connectionTimeout;
}
