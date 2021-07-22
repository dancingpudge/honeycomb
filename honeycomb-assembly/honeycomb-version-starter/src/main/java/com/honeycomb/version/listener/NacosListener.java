package com.honeycomb.version.listener;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.honeycomb.version.executor.RfNacosExecutor;
import com.honeycomb.version.properties.RfNacosProperties;
import com.honeycomb.version.properties.VersionProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @program: honeycomb-raven
 * @description: nacos监听
 * @author: wangzz
 * @create: 2021-04-07 10:11
 */
@Slf4j
@Component
@Data
@RefreshScope
@Profile({"dev", "test", "prod"})
public class NacosListener {

    private final VersionProperties versionProperties;
    private final RfNacosProperties rfNacosProperties;
    private final RfNacosExecutor rfNacosExecutor;

    @Autowired
    NacosListener(VersionProperties versionProperties, RfNacosProperties rfNacosProperties, RfNacosExecutor rfNacosExecutor) {

        this.versionProperties = versionProperties;
        this.rfNacosProperties = rfNacosProperties;
        this.rfNacosExecutor = rfNacosExecutor;
    }

    @PostConstruct
    private void listenConfig() {
        log.info("execute init======>");
        //初始化发送一次

        try {
            ConfigService configService = configServiceInit();

            configService.addListener(rfNacosProperties.getDataId(), rfNacosProperties.getGroup(), new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("receive : " + configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return rfNacosExecutor;
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置中心
     *
     * @return
     * @throws NacosException
     */
    private ConfigService configServiceInit() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, rfNacosProperties.getServerAddr());
        properties.put(PropertyKeyConst.USERNAME, rfNacosProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, rfNacosProperties.getPassword());
        properties.put(PropertyKeyConst.NAMESPACE, rfNacosProperties.getNamespace());
        return NacosFactory.createConfigService(properties);
    }

}
