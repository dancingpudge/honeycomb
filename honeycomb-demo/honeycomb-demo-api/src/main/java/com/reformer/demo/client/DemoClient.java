package com.honeycomb.demo.client;

import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.demo.entity.dto.DemoDTO;
import com.honeycomb.demo.entity.vo.DemoAccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * https://github.com/spring-cloud/spring-cloud-openfeign/tree/2.0.x
 * 在Spring Cloud OpenFeign中，除了OpenFeign自身提供的标注（annotation）之外，
 * 还可以使用JAX-RS标注，或者Spring MVC标注。下面还是以OpenFeign标注为例介绍用法。
 */
@FeignClient(name = "honeycomb-demo")
public interface DemoClient {

    /**
     * mysql-mybatis数据查询
     *
     * @param accountStatus
     * @return DemoVO
     */
    @RequestMapping(value = "/honeycomb-demo/demo/send", method = RequestMethod.POST)
    StandardResultVO<List<DemoAccountVO>> demoSend(@RequestParam("accountStatus") String accountStatus);

    /**
     * 参数校验与BeanCopier演示
     *
     * @param demoDTO
     * @return DemoVO
     */
    @RequestMapping(value = "/honeycomb-demo/demo/accept", method = RequestMethod.POST)
    StandardResultVO demoAccept(@RequestBody DemoDTO demoDTO);

    /**
     * 配置文件获取演示
     *
     * @return String
     */
    @RequestMapping(value = "/honeycomb-demo/demo/nacos-config", method = RequestMethod.POST)
    StandardResultVO nacosConfig();

    /**
     * redis存取值获取演示（redisTemplate与stringRedisTemplate序列化方式不一样，key不共享）
     *
     * @param name redisKey
     * @return String
     */
    @RequestMapping(value = "/honeycomb-demo/demo/springboot-redis", method = RequestMethod.POST)
    StandardResultVO redis(@RequestParam("redisKey") String name);

    /**
     * ApiException捕获演示，与表单传参
     */
    @RequestMapping(value = "/honeycomb-demo/demo/api-exception", method = RequestMethod.POST)
    void apiExcept();

}
