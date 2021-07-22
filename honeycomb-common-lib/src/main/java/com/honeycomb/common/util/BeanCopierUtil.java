package com.honeycomb.common.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PackgeName: com.honeycomb.common.util
 * @Description:
 * @Author: LiuH
 * @Date 2020-12-30 10:56:34
 **/
public class BeanCopierUtil {

    /**
     * BeanCopier缓存池
     */
    private static ConcurrentHashMap<String, SoftReference<BeanCopier>> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 对象集合拷贝
     *
     * @param src    原始数据
     * @param tClass 结果数据类型
     * @return List 目标对象Array
     */
    public static <R, T> List<T> copyList(List<R> src, Class<T> tClass) {
        List res = new ArrayList();
        if (CollectionUtils.isEmpty(src)) {
            return res;
        }
        BeanCopier copier = BeanCopierGetter(src.get(0).getClass(), tClass);
        src.forEach(a -> {
            try {
                Object obj = tClass.newInstance();
                copier.copy(a, obj, null);
                res.add(obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return res;
    }

    /**
     * 初始化拷贝对象
     *
     * @param src    原始对象
     * @param target 结果对象类型
     * @param <R>    返回结果类型
     * @param <T>    原始数据类型
     * @return R初始化的目标对象
     */
    public static <R, T> T initCopy(R src, Class<T> target) {
        if (!Optional.ofNullable(src).isPresent()) {
            return null;
        }
        T o = null;
        try {
            o = target.newInstance();
            BeanCopierGetter(src.getClass(), target).copy(src, o, null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 拷贝对象
     * 注意：只要字段名称和类型都匹配则都会进行拷贝
     *
     * @param src 原始对象
     * @param <R> 返回结果类型
     * @param <T> 原始数据类型
     * @return R 目标对象
     */
    public static <R, T> T copy(R src, T target) {
        if (!Optional.ofNullable(src).isPresent() || !Optional.ofNullable(target).isPresent()) {
            return target;
        }
        BeanCopierGetter(src.getClass(), target.getClass()).copy(src, target, null);
        return target;
    }

    /**
     * 获取缓存对象
     *
     * @param clzR
     * @param clzT
     * @return BeanCopier
     */
    private static synchronized BeanCopier BeanCopierGetter(Class clzR, Class clzT) {
        String key = clzR.getName() + clzT.toString();
        if (BEAN_COPIER_MAP.containsKey(key)) {
            return BEAN_COPIER_MAP.get(key).get();
        }
        BeanCopier beanCopier = BeanCopier.create(clzR, clzT, false);
        BEAN_COPIER_MAP.put(key, new SoftReference(beanCopier));
        return beanCopier;
    }
}
