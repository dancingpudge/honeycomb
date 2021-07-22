package com.honeycomb.common.feign.service;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.config.HoneycombFeignRetryConfig;
import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;
import com.honeycomb.common.util.DateTimeUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

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
public class FileRetryDataRecorder implements RetryDataRecorder {
    private final HoneycombFeignRetryConfig honeycombFeignRetryConfig;
    private static Logger log = LoggerFactory.getLogger(FileRetryDataRecorder.class.getName());

    @Autowired
    FileRetryDataRecorder(HoneycombFeignRetryConfig honeycombFeignRetryConfig) {
        this.honeycombFeignRetryConfig = honeycombFeignRetryConfig;
    }

    @Override
    public <T> void saveErrorFile(String clzName, String methodName, T data) {
        try {
            List<String> list = new ArrayList<>();
            list.add(JSONObject.toJSONString(new ReformerRetryInfo(clzName, methodName, JSONObject.toJSONString(data), data.getClass())));
            FileUtils.writeLines(Paths.get(honeycombFeignRetryConfig.getFileDir(), DateTimeUtil.getDateDisplayString(LocalDateTime.now())).toFile(),
                    "utf8", list, true);
        } catch (Exception e) {
            log.error("保存异常请求文件失败", e);
        }
    }

    @Override
    public FeignRetryEnum initStorageMode() {
        return FeignRetryEnum.FILE_STORAGE;
    }
}
