package com.honeycomb.portal.service;


import com.honeycomb.common.api.StandardResultVO;

/**
 * @Description 演示demo
 * @Author LiuH
 * @Date 2020/6/4
 */
public interface PortalService {
    /**
     * 演示样例
     * 功能注释写在接口上，不要放到具体实现上
     *
     * @return StandardResultVO
     */
    StandardResultVO demoSend(String accountStatus);
}
