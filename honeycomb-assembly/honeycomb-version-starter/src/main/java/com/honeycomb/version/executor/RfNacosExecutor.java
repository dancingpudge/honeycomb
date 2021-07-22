package com.honeycomb.version.executor;

import com.honeycomb.version.config.VersionAcquisition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @description: com.honeycomb.version.executor <br>
 * @date: 2021/4/9 10:23 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Slf4j
@Component
@Profile({"dev", "test", "prod"})
public class RfNacosExecutor implements Executor {

    private final VersionAcquisition versionAcquisition;

    @Autowired
    RfNacosExecutor(VersionAcquisition versionAcquisition) {
        this.versionAcquisition = versionAcquisition;
    }

    @Override
    public void execute(Runnable command) {
        versionAcquisition.saveVersion();
    }
}
