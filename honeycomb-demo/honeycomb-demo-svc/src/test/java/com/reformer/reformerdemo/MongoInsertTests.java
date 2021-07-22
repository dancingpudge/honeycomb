package com.honeycomb.honeycombdemo;

import com.honeycomb.common.util.DateTimeUtil;
import com.honeycomb.demo.ReformerDemoApplication;
import com.honeycomb.demo.entity.po.RfDemoPO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Liuh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReformerDemoApplication.class)
class MongoInsertTests {
    @Resource
    MongoTemplate mongoTemplate;

    String DEMO_TABLE = "rf_demo";

    @Test
    void insert() {
        RfDemoPO rfDemoPO = new RfDemoPO();
        rfDemoPO.setCarParkId("0000");
        rfDemoPO.setUid(rfDemoPO.getUid());
        rfDemoPO.setId("111");
        rfDemoPO.setCreateTime(DateTimeUtil.getDateTimeDisplayString());
        mongoTemplate.insert(rfDemoPO, DEMO_TABLE);
    }
}
