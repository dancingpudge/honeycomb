package com.honeycomb.dingtalk;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 钉钉推送的配置类，通过@EnableConfigurationProperties(DingTalkProperties.class)开启
 *
 * @version 1.0
 * @author: Fan
 * @date 2020.12.28 9:28
 */
@Data
@ConfigurationProperties(prefix = "dingtalk")
public class DingTalkProperties {

  private Boolean open;
  private String webhook;
  private Boolean isAtAll;
  private String atMobiles;
  private String messagePrefix;

}
