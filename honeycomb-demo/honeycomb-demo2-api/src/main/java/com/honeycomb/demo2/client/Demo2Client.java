package com.honeycomb.demo2.client;

import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.api.StandardPageResultVO;
import com.honeycomb.common.entity.dto.PageForm;
import com.honeycomb.demo2.constant.Demo2ApiConstant;
import com.honeycomb.demo2.entity.dto.Demo2DTO;
import com.honeycomb.demo2.entity.dto.DemoAccountDTO;
import com.honeycomb.demo2.entity.vo.Demo2VO;
import com.honeycomb.demo2.fallback.Demo2ClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * https://github.com/spring-cloud/spring-cloud-openfeign/tree/2.0.x
 * 在Spring Cloud OpenFeign中，除了OpenFeign自身提供的标注（annotation）之外，
 * 还可以使用JAX-RS标注，或者Spring MVC标注。下面还是以OpenFeign标注为例介绍用法。
 */
@FeignClient(name = "honeycomb-demo2", fallback = Demo2ClientFallBack.class)
public interface Demo2Client {

    /**
     * openfeign测试
     *
     * @param demoDto
     * @return StandardResultVO
     */
    @RequestMapping(Demo2ApiConstant.SEND)
    StandardResultVO<Demo2VO> demoTest(@RequestBody Demo2DTO demoDto);

    @RequestMapping(Demo2ApiConstant.SAVE)
    StandardResultVO<Integer> save(@RequestBody DemoAccountDTO demoAccountDTO);

    @RequestMapping(Demo2ApiConstant.QUERY_PAGE)
    StandardPageResultVO getPage(@RequestBody PageForm pageForm);
}
