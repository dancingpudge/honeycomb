package com.honeycomb.version.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


/**
 * @program: honeycomb-raven
 * @description: 版本配置
 * @author: wangzz
 * @create: 2021-04-08 14:31
 */
@Data
@Component
@RefreshScope
@Profile({"dev", "test", "prod"})
public class VersionProperties {
    /**
     * 项目版本号
     */
    @Value("${version.svn.version}")
    private String projectVersion;
    /**
     * svn的提交版本
     */
    @Value("${version.svn.svn_revision}")
    private String svnRevision;
    /**
     * jenkins构建数
     */
    @Value("${version.svn.build_number}")
    private String buildNumber;

    /**
     * 应用名称
     */
    @Value("${spring.application.name:null}")
    private String applicationName;
    /**
     * 项目域名
     */
    @Value("${honeycomb.version.domain}")
    private String domainName;
}
