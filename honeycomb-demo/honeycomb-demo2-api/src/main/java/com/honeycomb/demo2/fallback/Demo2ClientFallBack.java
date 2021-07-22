package com.honeycomb.demo2.fallback;

import com.honeycomb.common.api.StandardPageResultVO;
import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.entity.dto.PageForm;
import com.honeycomb.common.feign.FeignRetryHandler;
import com.honeycomb.demo2.client.Demo2Client;
import com.honeycomb.demo2.entity.dto.Demo2DTO;
import com.honeycomb.demo2.entity.dto.DemoAccountDTO;
import com.honeycomb.demo2.entity.vo.Demo2VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: com.honeycomb.demo2.fallback <br>
 * @date: 2021/1/18 5:25 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Slf4j
@Component
public class Demo2ClientFallBack implements Demo2Client {
    @Resource
    FeignRetryHandler feignRetryHandler;

    @Override
    public StandardResultVO<Demo2VO> demoTest(Demo2DTO demoDto) {
        log.debug("====>    记录熔断信息");
        feignRetryHandler.recorder(Demo2Client.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), demoDto);
        return StandardResultVO.hystrixResult();
    }

    @Override
    public StandardResultVO<Integer> save(DemoAccountDTO demoAccountDTO) {
        return null;
    }

    @Override
    public StandardPageResultVO getPage(PageForm pageForm) {
        return null;
    }
}
