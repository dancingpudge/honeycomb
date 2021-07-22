package com.honeycomb.portal.service.impl;

import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.demo.client.DemoClient;
import com.honeycomb.demo.entity.vo.DemoAccountVO;
import com.honeycomb.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description 华夏银行电子钱包回调相关
 * @Author Liuh
 * @Date 2020/6/4
 */
@Slf4j
@Service("portalService")
public class PortalServiceImpl implements PortalService {
    @Resource
    DemoClient demoClient;

    @Override
    public StandardResultVO<List<DemoAccountVO>> demoSend(String accountStatus) {
        return demoClient.demoSend(accountStatus);
    }
}
