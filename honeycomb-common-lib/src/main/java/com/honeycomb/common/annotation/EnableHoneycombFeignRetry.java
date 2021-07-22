package com.honeycomb.common.annotation;

import java.lang.annotation.*;

/**
 * feign调用失败后持久化方案
 * <p>
 * 在使用feign进行调用的时候可能会遇到调用失败的情况
 * eg：网络波动，服务重启等等远端服务暂不可用的情况。
 * <p>
 * 本身使用了springcloud Nacos + Feign+Hystrix+Ribbon实现了重试。
 * <p>
 * 但是，我们还想记录失败的情况，共后续追踪、告警、重试、人工介入等方案
 * 所以尝试使用三步走的方案 STEP1:抓取错误信息 STEP2：持久华详情 STEP3：介入方案实现
 *
 * 注意（restful风格下）：
 * 没必要重试的请求不会做记录：GET
 * 需要重试的接口：POST，DELETE！，PATCH 。POST，PATCH需要支持幂等，DELETE由于天然满足幂等性无需特殊关注
 *
 * @Author LiuH
 * @Date 2021/01/18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableHoneycombFeignRetry {
}
