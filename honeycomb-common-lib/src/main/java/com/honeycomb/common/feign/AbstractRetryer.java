package com.honeycomb.common.feign;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.common.annotation.EnableHoneycombFeignRetry;
import com.honeycomb.common.feign.entity.ReformerRetryInfo;
import com.honeycomb.common.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description: com.honeycomb.common.feign <br>
 * @date: 2021/1/19 2:27 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
@ConditionalOnBean(annotation = EnableHoneycombFeignRetry.class)
public class AbstractRetryer {
    private static final Logger log = LoggerFactory.getLogger(AbstractRetryer.class.getName());
    private final ConcurrentHashMap clientMap = new ConcurrentHashMap();
    @Autowired
    FeignRetryFactory feignRetryFactory;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void init() {
        feignRetryFactory.getRetryStrategy()
                //获取之前发送失败的记录
                .collect().stream()
                .distinct()
                //去重后重试
                .forEach(d->retry(d));
    }

    /**
     * 重试调用
     *
     * @param d 需要重试的信息
     */
    public void retry(ReformerRetryInfo d) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("=======》 发起重试： ").append(JSONObject.toJSONString(d));
            Object bean = getBean(d.getClzName());
            Method method = ReflectionUtils.findMethod(bean.getClass(), d.getMethodName(), d.getParamClz());
            ReflectionUtils.invokeMethod(method, bean, JSONObject.parseObject(d.getData(), d.getParamClz()));
            //等待防止对子系统造成过大并发压力
            TimeUnit.MILLISECONDS.sleep(200);
            sb.append("\n=======》 重试结束： " + JSONObject.toJSONString(d));
        } catch (Exception e) {

        } finally {
            log.info(sb.toString());
        }

    }

    /**
     * 获取之前失败的方法
     *
     * @param clzName
     * @return
     */
    public synchronized Object getBean(String clzName) {
        if (clientMap.containsKey(clzName)) {
            return clientMap.get(clzName);
        }

        try {
            Object o = ApplicationContextUtil.getBean(Class.forName(clzName));
            clientMap.put(clzName, o);
            return o;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
