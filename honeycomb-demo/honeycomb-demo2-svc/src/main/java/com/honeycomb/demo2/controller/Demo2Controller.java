package com.honeycomb.demo2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.honeycomb.common.api.PageUtil;
import com.honeycomb.common.api.StandardPageResultVO;
import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.entity.dto.PageForm;
import com.honeycomb.demo2.entity.dto.Demo2DTO;
import com.honeycomb.demo2.entity.dto.DemoAccountDTO;
import com.honeycomb.demo2.entity.vo.Demo2VO;
import com.honeycomb.demo2.entity.vo.DemoAccountVO;
import com.honeycomb.demo2.service.DemoAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: honeycomb-parent
 * @description: 实例模板 swagger、openfeign、参数强类型校验、异常捕获
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
@Api("演示相关接口")
@Slf4j
@RestController
@RefreshScope
@RequestMapping("/demo2")
public class Demo2Controller {

    @Autowired
    private DemoAccountService demoAccountService;

    @ApiOperation(value = "openfeign调用演示")
    @PostMapping(value = "/send")
    public StandardResultVO<Demo2VO> demoSend(@RequestBody Demo2DTO demoDto) {
        log.info("============>");
        throw new RuntimeException("重试功能测试");
    }


    @PostMapping("/save")
    public StandardResultVO<Integer> save(@RequestBody DemoAccountDTO demoAccountDTO){
      return StandardResultVO.successResult(demoAccountService.insertDemoAccount(demoAccountDTO));
    }

    @PostMapping("/query-page")
    public StandardPageResultVO getPage(@RequestBody PageForm pageForm){
        IPage<DemoAccountVO> demoAccountPage = demoAccountService.getDemoAccountPage(pageForm.getCurPage(), pageForm.getPageSize());
        PageUtil pageUtil =new PageUtil(demoAccountPage.getCurrent(),demoAccountPage.getSize());
        return StandardPageResultVO.successResult(pageUtil,demoAccountPage.getRecords());
    }
}
