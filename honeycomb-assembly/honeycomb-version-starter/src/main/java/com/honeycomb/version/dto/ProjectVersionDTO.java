package com.honeycomb.version.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: honeycomb-device
 * @description: 项目版本
 * @author: wangzz
 * @create: 2021-04-07 14:26
 */
@Data
public class ProjectVersionDTO {

    @ApiModelProperty("域名")
    @JsonProperty("domain_name")
    private String domainName;

    /**
     * 项目名称
     */
    @ApiModelProperty("项目名称")
    @JsonProperty("project_name")
    private String projectName;

    /**
     * svn提交版本
     */
    @ApiModelProperty("svn提交版本")
    @JsonProperty("svn_revision")
    private String svnRevision;

    /**
     * jenkins构建数
     */
    @ApiModelProperty("jenkins构建数")
    @JsonProperty("build_number")
    private String buildNumber;

    @ApiModelProperty("项目的版本")
    @JsonProperty("project_version")
    private String projectVersion;

}
