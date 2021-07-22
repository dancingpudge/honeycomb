package com.honeycomb.common.util;

/**
 * @description: com.honeycomb.common.util <br>
 * @date: 2021/2/26 3:25 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@FunctionalInterface
public interface ObjectFeaturesFunction<T> {

    /**
     * 对象特征值提取方法
     *
     * @param o
     * @return 对象特征值
     */
    String features(T o);

}
