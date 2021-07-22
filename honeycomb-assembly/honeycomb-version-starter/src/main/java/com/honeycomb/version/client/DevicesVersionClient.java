package com.honeycomb.version.client;

import com.honeycomb.common.api.StandardResultVO;
import com.honeycomb.version.dto.DevicesApiConstant;
import com.honeycomb.version.dto.ProjectVersionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: honeycomb-raven
 * @description: 设备服务
 * @author: wangzz
 * @create: 2021-03-23 16:54
 */
@FeignClient(name = "version", url = "${honeycomb.devices.domain:null}", contextId = "devicesVersion")
public interface DevicesVersionClient {

    /**
     * 保存项目版本
     *
     * @param projectVersionDTO
     * @return
     */
    @PostMapping(DevicesApiConstant.PROJECT_VERSION)
    StandardResultVO<Boolean> saveProjectVersion(@RequestBody ProjectVersionDTO projectVersionDTO);

}
