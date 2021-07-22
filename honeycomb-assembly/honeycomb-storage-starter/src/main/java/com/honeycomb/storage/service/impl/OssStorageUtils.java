package com.honeycomb.storage.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.honeycomb.common.entity.constant.CommonConstant;
import com.honeycomb.common.util.DateTimeUtil;
import com.honeycomb.common.util.StackTraceLogUtil;
import com.honeycomb.storage.config.ReformerOssProperties;
import com.honeycomb.storage.exception.OSSFileException;
import com.honeycomb.storage.service.StorageUtils;
import com.honeycomb.storage.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: com.honeycomb.storage.service.impl <br>
 * @date: 2021/2/20 4:22 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Slf4j
@Service
public class OssStorageUtils implements StorageUtils {

    /**
     * 一分钟对应毫秒数
     */
    private final static int ONE_MINUTE = 1000 * 60;
    public ReformerOssProperties properties;
    private OSS ossClient;

    @Autowired
    public OssStorageUtils(ReformerOssProperties honeycombOssProperties) {
        this.properties = honeycombOssProperties;
        this.ossClient = initOssClient();
    }

    /**
     * 生成oss客户端连接
     *
     * @return OSS
     */
    private OSS initOssClient() {
        return new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret());
    }

    /**
     * 生成OssKey文件前的路径
     *
     * @param fileKey
     * @return OssKey
     */
    private String initPrefix(String fileKey) {
        return StringUtils.isEmpty(properties.getPrefix()) ? DateTimeUtil.getDateDisplayString() + "/" + fileKey
                : properties.getPrefix() + DateTimeUtil.getDateDisplayString() + "/" + fileKey;
    }

    @Override
    public String uploadFile(String fileKey, InputStream inputStream) {
        String objectName = "";
        try {
            // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            objectName = initPrefix(fileKey);
            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            ossClient.putObject(properties.getBucketName(), objectName, inputStream);
        } catch (OSSException | NullPointerException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
        }
        return objectName;
    }

    @Override
    public void uploadFileByAbsolutePath(String fileKey, InputStream inputStream) {
        try {
            // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            ossClient.putObject(properties.getBucketName(), fileKey, inputStream);
        } catch (OSSException | NullPointerException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
        }
    }

    @Override
    public String uploadFile(byte[] bFile, String path) {
        if (path.startsWith("\\") || path.startsWith("/")) {
            path = path.substring(1);
        }
        try (InputStream inputStream = new ByteArrayInputStream(bFile)) {
            return uploadByInputStream(path, inputStream);
        } catch (Exception e) {
            log.info(StackTraceLogUtil.getStackTraceAsString(e));
        }
        return "";
    }

    @Override
    public String fileDownLoadByKey(String fileKey, Integer lastMinute) {
        String url = "";
        try {
            Date expiredTime = new Date(System.currentTimeMillis() + lastMinute * ONE_MINUTE);
            url = ossClient.generatePresignedUrl(properties.getBucketName(), fileKey, expiredTime).toString();
        } catch (OSSException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
        }
        return url;
    }

    @Override
    public boolean fileExist(String fileKey) {
        Boolean exist = false;
        try {
            exist = ossClient.doesObjectExist(properties.getBucketName(), fileKey);
        } catch (OSSException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
        }
        return exist;
    }

    @Override
    public void fileDelete(String fileKey) {
        try {
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(properties.getBucketName(), fileKey);
        } catch (OSSException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
        }
    }

    private String uploadByInputStream(String path, InputStream inputStream) throws Exception {
        String ossKey = "";
        try {
            Map<String, String> pathMap = FileUtils.parsePath(path);
            String fileDir = pathMap.get("fileDir").replace("\\", "/");
            String fileName = pathMap.get("fileName");
            String fileType = pathMap.get("fileType");
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(fileType);
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            ossClient.putObject(properties.getBucketName(), fileDir + "/" + fileName, inputStream, objectMetadata);
            ossKey = fileDir + "/" + fileName;
        } catch (OSSException oe) {
            log.info(StackTraceLogUtil.getStackTraceAsString(oe));
            throw new OSSFileException(60001, String.format("图片上传失败,失败原因：%s", new Object[]{JSON.toJSONString(initOSSException(oe))}));
        } finally {
            //显示关闭流
            return ossKey;
        }
    }

    /**
     * 组装OSS异常
     *
     * @param oe
     * @return
     */
    private Map<String, String> initOSSException(OSSException oe) {
        Map<String, String> reason = new HashMap<>(CommonConstant.MAP_INIT_SIZE);
        reason.put("reason",
                "Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
        reason.put("Error Message:", oe.getErrorMessage());
        reason.put("Error Code:", oe.getErrorCode());
        reason.put("Request ID:", oe.getRequestId());
        reason.put("Host ID:", oe.getHostId());
        return reason;
    }

    /**
     * 生成OSS bucket（此方法直接从其他项目copy，未验证，验证后请删除此注释）
     *
     * @param imageExpire 过期时间（天）
     */
    private void initBucket(Integer imageExpire) {
        boolean exists = ossClient.doesBucketExist(properties.getBucketName());
        if (!exists) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(properties.getBucketName());
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            createBucketRequest.setStorageClass(StorageClass.Standard);
            ossClient.createBucket(createBucketRequest);
            String ruleId = imageExpire + "天过期删除";
            String matchPrefix = "";
            LifecycleRule rule = new LifecycleRule(ruleId, matchPrefix, LifecycleRule.RuleStatus.Enabled, imageExpire.intValue());
            SetBucketLifecycleRequest requestLife = new SetBucketLifecycleRequest(properties.getBucketName());
            requestLife.AddLifecycleRule(rule);
            ossClient.setBucketLifecycle(requestLife);
            SetBucketTaggingRequest requestTag = new SetBucketTaggingRequest(properties.getBucketName());
            requestTag.setTag("create", DateTimeUtil.getDateTimeDisplayString());
            requestTag.setTag("备注", imageExpire + "天过期删除,从文件上传开始计算");
            ossClient.setBucketTagging(requestTag);
        }
    }
}
