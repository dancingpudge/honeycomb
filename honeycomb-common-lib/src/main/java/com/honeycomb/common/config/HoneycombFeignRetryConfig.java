package com.honeycomb.common.config;

import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: com.honeycomb.common.config <br>
 * @Date: 2021/1/18 10:53 上午 <br>
 * @Author: liuhui <br>
 */
@Configuration
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
@ConfigurationProperties(prefix = "honeycomb.feign.retry")
public class HoneycombFeignRetryConfig {

    /**
     * 数据存储方案
     */
    private String storageMode = "file";
    /**
     * 数据存储位置
     */
    private String fileDir = "honeycomb-feign-retry";


    public String getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(String storageMode) {
        this.storageMode = storageMode;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

}
