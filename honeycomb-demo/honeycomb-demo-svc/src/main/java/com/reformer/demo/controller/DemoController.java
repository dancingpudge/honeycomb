package com.honeycomb.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.honeycomb.common.annotation.BusinessLog;
import com.honeycomb.common.api.PageUtil;
import com.honeycomb.common.api.StandardPageResultVO;
import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.common.base.exception.ApiException;
import com.honeycomb.common.redis.JedisClient;
import com.honeycomb.common.redis.RedisLockUtils;
import com.honeycomb.common.util.BeanCopierUtil;
import com.honeycomb.common.util.DateTimeUtil;
import com.honeycomb.demo.config.KafkaProducer;
import com.honeycomb.demo.entity.dto.DemoDTO;
import com.honeycomb.demo.entity.dto.PageDemoDTO;
import com.honeycomb.demo.entity.po.DemoAccount;
import com.honeycomb.demo.entity.po.RfDemoPO;
import com.honeycomb.demo.entity.vo.DemoAccountVO;
import com.honeycomb.demo.service.DemoService;
import com.honeycomb.demo2.entity.dto.Demo2DTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/demo")
public class DemoController {
    private final DemoService demoService;

    @Autowired
    JedisClient jedisClient;
    @Autowired
    MongoTemplate mongoTemplate;
    @Resource
    KafkaProducer kafkaProducer;
    @Resource
    RedisLockUtils redisLockUtils;
    @Resource
    RedissonClient redissonClient;


    @Value(value = "${honeycomb.test:test2}")
    String test;

    @Value("${spring.kafka.producer.topic-name}")
    String topicName;

    @Autowired
    DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


    @ApiOperation(value = "mysql-mybatis数据查询")
    @PostMapping(value = "/send")
    public StandardResultVO<List<DemoAccountVO>> demoSend(@RequestParam String accountStatus) {
        List<DemoAccount> accountList = demoService.listAccountByStatus(accountStatus);
        return StandardResultVO.successResult(BeanCopierUtil.copyList(accountList, DemoAccountVO.class));
    }

    @ApiOperation(value = "参数校验与BeanCopier演示")
    @PostMapping(value = "/accept")
    @BusinessLog(systemModule = "DemoModule")
    public StandardResultVO demoAccept(@Validated @RequestBody DemoDTO demoDto) {
        log.info("========> demoDto: " + demoDto);
        Demo2DTO demo2Dto = BeanCopierUtil.initCopy(demoDto, Demo2DTO.class);
        log.info("========> walletDemoDto: " + JSON.toJSONString(demo2Dto));
        return StandardResultVO.successResult(demoService.demoTest(demo2Dto));
    }

    @ApiOperation(value = "配置文件获取演示")
    @PostMapping(value = "/nacos-config")
    public String nacosConfig() {
        log.info("配置中心获取 test：=========>" + test);
        return test;
    }

    @SneakyThrows
    @ApiOperation(value = "redis存取值获取演示（redisTemplate与stringRedisTemplate序列化方式不一样，key不共享）")
    @PostMapping(value = "/springboot-redis")
    public StandardResultVO redis(@RequestParam(name = "redisKey", defaultValue = "key") String name) {
        jedisClient.setnx("name:" + name, name);
        redisLockUtils.execute("", () -> {
            System.out.println();
        });
        return StandardResultVO.successResult("stringRedisTemplate: " + jedisClient.get("name:" + name)
                + " redisTemplate: " + jedisClient.get("name:" + name));
    }

    @ApiOperation(value = "ApiException捕获演示，与表单传参")
    @PostMapping(value = "/api-exception")
    public void apiExcept() {
        throw new ApiException("10000");
    }

    @ApiOperation(value = "mongo入库演示，与表单传参")
    @PostMapping(value = "/mongo/insert")
    public StandardResultVO mongoInsert() {
        RfDemoPO rfDemoPO = new RfDemoPO();
        rfDemoPO.setCarParkId("0000");
        rfDemoPO.setUid(rfDemoPO.getUid());
        rfDemoPO.setId(RandomStringUtils.randomNumeric(4));
        rfDemoPO.setCreateTime(DateTimeUtil.getDateTimeDisplayString());
        return StandardResultVO.successResult(mongoTemplate.insert(rfDemoPO));
    }

    @ApiOperation(value = "mongo入库演示，与表单传参")
    @PostMapping(value = "/mongo/search")
    public StandardResultVO<List<RfDemoPO>> mongoSearch() {
        return StandardResultVO.successResult(mongoTemplate.findAll(RfDemoPO.class));
    }

    @ApiOperation(value = "kafka数据推送")
    @PostMapping(value = "/kafka/produce")
    public StandardResultVO<Boolean> kafkaProduce() {
        JSONObject msg = new JSONObject();
        msg.put("carParkId", "1111");
        msg.put("businessId", "1111");
        kafkaProducer.doProduce(msg, topicName);
        return StandardResultVO.successResult();
    }

    @ApiOperation(value = "分页模板代码")
    @PostMapping(value = "/page/demo")
    public StandardPageResultVO<List<DemoAccountVO>> kafkaProduce(@RequestBody PageDemoDTO pageDemoDTO) {
        //PageInfo为任意选用的分页插件
        PageInfo<RfDemoPO> accountList = demoService.countAdminPage(pageDemoDTO);

        List<DemoAccountVO> accountVOList = new ArrayList();
        accountList.getList().forEach(t -> accountVOList.add(convert(t)));

        PageUtil pageUtil = new PageUtil(Long.valueOf(accountList.getStartRow()), Long.valueOf(accountList.getPageSize()));
        pageUtil.setTotalRows(accountList.getTotal());
        return StandardPageResultVO.successResult(pageUtil, accountVOList);
    }

    public static DemoAccountVO convert(RfDemoPO t) {
        return new DemoAccountVO();
    }


}
