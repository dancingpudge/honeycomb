package com.honeycomb.common.feign.service;

import com.honeycomb.common.feign.entity.FeignRetryEnum;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/18 8:08 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public interface RetryDataRecorder {

    /**
     * 保存发送异常信息
     *
     * @param clzName    数据
     * @param methodName 数据
     * @param data       数据
     */
    <T> void saveErrorFile(String clzName, String methodName, T data);

    /**
     * 信息存储策略
     * @return
     */
    FeignRetryEnum initStorageMode();
}
