package com.honeycomb.common.feign.service;

import com.honeycomb.common.feign.entity.FeignRetryEnum;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;

import java.util.List;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/18 8:08 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public interface RetryDataRetryer {

    /**
     * 获取需重试数据
     *
     * @return
     */
    List<ReformerRetryInfo> collect();

    /**
     * 确定文件存储方案
     *
     * @return
     */
    FeignRetryEnum initStorageMode();
}
