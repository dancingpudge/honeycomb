package com.honeycomb.version.config;

import com.alibaba.fastjson.JSONObject;
import com.honeycomb.version.client.DevicesVersionClient;
import com.honeycomb.version.dto.ProjectVersionDTO;
import com.honeycomb.version.properties.VersionProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: honeycomb-raven
 * @description: 版本获取
 * @author: wangzz
 * @create: 2021-04-07 10:21
 */
@Component
@Data
@Slf4j
@RefreshScope
@Profile({"dev", "test", "prod"})
public class VersionAcquisition {

    private final DevicesVersionClient devicesClient;
    private final VersionProperties versionProperties;

    @Autowired
    VersionAcquisition(DevicesVersionClient devicesClient, VersionProperties versionProperties) {
        this.devicesClient = devicesClient;
        this.versionProperties = versionProperties;
    }

    public void saveVersion() {
        new Thread(() -> {
            log.debug("execute start======>");
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    //获取配置
                    ProjectVersionDTO dto = initProp();
                    //推送
                    log.info("saveVersion =======> dto :" + JSONObject.toJSONString(dto));
                    devicesClient.saveProjectVersion(dto);
                    break;
                } catch (Exception e) {
                    log.error("推送失败:{}", e.fillInStackTrace());
                } finally {
                    log.info("saveVersion =======> finished");
                }
            }
        }, "RfNacosExecutor: ").start();
    }

    /**
     * 获取配置
     *
     * @return ProjectVersionDTO
     */
    private ProjectVersionDTO initProp() {
        log.debug("推送项目:{},项目版本:{},域名:{},svn提交版本:{},jenkins构建数:{}", versionProperties.getApplicationName(), versionProperties.getProjectVersion(),
                versionProperties.getDomainName(), versionProperties.getSvnRevision(),
                versionProperties.getBuildNumber());

        //等待配置更新
        ProjectVersionDTO projectVersionDTO = new ProjectVersionDTO();
        projectVersionDTO.setProjectVersion(versionProperties.getProjectVersion());
        projectVersionDTO.setProjectName(versionProperties.getApplicationName());
        projectVersionDTO.setDomainName(versionProperties.getDomainName());
        projectVersionDTO.setSvnRevision(versionProperties.getSvnRevision());
        projectVersionDTO.setBuildNumber(versionProperties.getBuildNumber());
        return projectVersionDTO;
    }

}
