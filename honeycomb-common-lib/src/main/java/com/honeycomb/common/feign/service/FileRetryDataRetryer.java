package com.honeycomb.common.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.config.HoneycombFeignRetryConfig;
import com.honeycomb.common.feign.AbstractRetryer;
import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;
import com.honeycomb.common.util.DateTimeUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: com.honeycomb.common.aspect <br>
 * @date: 2021/1/18 11:17 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
public class FileRetryDataRetryer extends AbstractRetryer implements RetryDataRetryer {
    private final HoneycombFeignRetryConfig honeycombFeignRetryConfig;
    private static Logger log = LoggerFactory.getLogger(FileRetryDataRetryer.class.getName());

    @Autowired
    FileRetryDataRetryer(HoneycombFeignRetryConfig honeycombFeignRetryConfig) {
        this.honeycombFeignRetryConfig = honeycombFeignRetryConfig;
    }

    @Override
    public List<ReformerRetryInfo> collect() {
        List<ReformerRetryInfo> honeycombRetryInfos = new ArrayList<>();
        try {
            //当天的每次都执行重试，隔天的偶数小时才执行
            for (int i = 1; i < 3 && LocalDateTime.now().getHour() / i == 0; i++) {
                File file = Paths.get(honeycombFeignRetryConfig.getFileDir(), DateTimeUtil.getDateDisplayString(LocalDateTime.now().minusDays(i))).toFile();
                if (!file.exists()) {
                    continue;
                }
                //读取文件
                FileUtils.readLines(file, "utf8").forEach(d -> honeycombRetryInfos.add(JSONObject.parseObject(d, ReformerRetryInfo.class)));
                //删除文件
                FileUtils.deleteQuietly(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return honeycombRetryInfos;
    }

    @Override
    public FeignRetryEnum initStorageMode() {
        return FeignRetryEnum.FILE_STORAGE;
    }
}
