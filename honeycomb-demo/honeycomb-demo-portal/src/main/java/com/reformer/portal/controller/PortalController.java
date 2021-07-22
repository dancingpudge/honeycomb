package com.honeycomb.portal.controller;

import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.demo.entity.vo.DemoAccountVO;
import com.honeycomb.portal.service.PortalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: honeycomb-portal
 * @description: 实例模板 swagger、openfeign
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
@Api("portal演示相关接口")
@RestController
@RequestMapping("/portal")
public class PortalController {

    private final PortalService portalService;

    @Autowired
    PortalController(PortalService portalService) {
        this.portalService = portalService;
    }


    @ApiOperation(value = "openfeign调用演示,通过应用名称访问在线的服务")
    @PostMapping(value = "/send")
    public StandardResultVO<List<DemoAccountVO>> demoSend(
            @RequestParam(value = "accountStatus", defaultValue = "ACTIVE") String accountStatus
    ) {
        return portalService.demoSend(accountStatus);
    }

}
