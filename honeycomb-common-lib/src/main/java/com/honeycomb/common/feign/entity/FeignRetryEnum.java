package com.honeycomb.common.feign.entity;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/19 11:02 上午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public enum FeignRetryEnum {
    FILE_STORAGE("file", "文件存储"), REDIS_STORAGE("redis", "redis存储");

    FeignRetryEnum(String storageMode, String mark) {
        this.storageMode = storageMode;
        this.mark = mark;
    }

    private String storageMode;
    private String mark;

    public static FeignRetryEnum getByStorageMode(String storageMode) {
        switch (storageMode) {
            case "redis":
                return REDIS_STORAGE;
            default:
                return FILE_STORAGE;
        }
    }

    public String getStorageMode() {
        return storageMode;
    }

    public void setStorageMode(String storageMode) {
        this.storageMode = storageMode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
