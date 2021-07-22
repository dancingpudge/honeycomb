package com.honeycomb.storage.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: com.honeycomb.storage <br>
 * @date: 2021/2/20 4:06 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Configuration
@EnableConfigurationProperties({ReformerOssProperties.class, ReformerObsProperties.class})
public class ReformerStorageConfig {
 /*   @Autowired
    List<StorageUtils> storageUtils;

    Class storageUtilsClz;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        storageUtils.forEach(s -> {
            if (s instanceof OssStorageUtils && Optional.ofNullable(((OssStorageUtils) s).properties.getBucketName()).isPresent()) {
                storageUtilsClz = OssStorageUtils.class;
                return;
            }
            if (s instanceof ObsStorageUtils && Optional.ofNullable(((ObsStorageUtils) s).properties.getBucketName()).isPresent()) {
                storageUtilsClz = ObsStorageUtils.class;
                return;
            }
        });
    }

    @Bean
    StorageUtils storageUtils() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return storageUtilsClz == OssStorageUtils.class ? (OssStorageUtils) storageUtilsClz.getConstructor().newInstance()
                : (ObsStorageUtils) storageUtilsClz.getConstructor().newInstance();
    }*/
}
