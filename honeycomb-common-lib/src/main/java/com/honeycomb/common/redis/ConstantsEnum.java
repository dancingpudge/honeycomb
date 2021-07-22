package com.honeycomb.common.redis;

/**
 * @author admin
 */

public enum ConstantsEnum {
    /**
     * 车场余位信息在redis中的key后缀
     * 如:512694_free_data
     */
    _free_data,
    /**
     * 占位字符
     */
    occupied,
    /***
     * 所有车场的key
     */
    all_park_id_key,
    /**
     * redis中进出场数据头
     */
    ioHead,
    /**
     * redis中图片数据头
     */
    picHead
}
