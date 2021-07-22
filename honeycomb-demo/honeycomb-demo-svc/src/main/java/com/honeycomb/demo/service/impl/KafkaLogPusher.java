package com.honeycomb.demo.service.impl;

import com.honeycomb.common.log.BusinessLogVO;
import com.honeycomb.common.log.LogPusher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @program: honeycomb-parent
 * @description:
 * @author: LiuH
 * @create: 2020-12-03 18:57
 **/

@Component("logPusher")
public class KafkaLogPusher implements LogPusher {
    private static Logger log = LoggerFactory.getLogger(KafkaLogPusher.class.getName());

    @Override
    public void push(BusinessLogVO vo) {
        log.info("=======>日志记录： vo " +vo.toString());
    }
}
