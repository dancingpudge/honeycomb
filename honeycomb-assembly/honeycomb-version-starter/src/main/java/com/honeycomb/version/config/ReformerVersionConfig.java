package com.honeycomb.version.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

/**
 * @description: com.honeycomb.storage <br>
 * @date: 2021/2/20 4:06 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Configuration
@Profile({"dev", "test", "prod"})
public class ReformerVersionConfig {

    @Autowired
    VersionAcquisition versionAcquisition;

    @PostConstruct
    public void init() {
        versionAcquisition.saveVersion();
    }
}
