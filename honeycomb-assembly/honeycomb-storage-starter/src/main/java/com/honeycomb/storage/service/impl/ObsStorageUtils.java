package com.honeycomb.storage.service.impl;

import com.honeycomb.storage.service.StorageUtils;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.*;
import com.obs.services.model.fs.NewFolderRequest;
import com.honeycomb.storage.config.ReformerObsProperties;
import com.honeycomb.storage.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: com.honeycomb.storage.service.impl <br>
 * @date: 2021/2/20 4:22 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Service
public class ObsStorageUtils implements StorageUtils {

    /**
     * 一分钟对应毫秒数
     */
    private final static int ONE_MINUTE = 1000 * 60;
    public ReformerObsProperties properties;

    @Autowired
    public ObsStorageUtils(ReformerObsProperties honeycombOssProperties) {
        this.properties = honeycombOssProperties;
    }

    /**
     * 生成obs客户端连接
     *
     * @return OSS
     */
    private ObsClient getObsClient() {
        ObsConfiguration obsConfiguration = new ObsConfiguration();
        obsConfiguration.setConnectionTimeout(properties.getConnectionTimeout().intValue() * 1000);
        obsConfiguration.setEndPoint(properties.getEndPoint());
        ObsClient obsClient = new ObsClient(properties.getAccessKey(), properties.getSecretKey(), obsConfiguration);
        boolean existBucket = obsClient.headBucket(properties.getBucketName());
        if (!existBucket) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(properties.getBucketName());
            createBucketRequest.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
            createBucketRequest.setBucketStorageClass(StorageClassEnum.STANDARD);
            createBucketRequest.setAvailableZone(AvailableZoneEnum.MULTI_AZ);
            obsClient.createBucket(createBucketRequest);
        }
        return obsClient;
    }

    @Override
    public String uploadFile(String fileKey, InputStream inputStream) {
        //todo 待实现
        return "";
    }

    @Override
    public void uploadFileByAbsolutePath(String fileKey, InputStream inputStream) throws IOException {
        //todo 待实现
    }

    @Override
    public String uploadFile(byte[] bFile, String path) throws Exception {
        path = path.replace("\\", "/");
        try {
            if (path.startsWith("\\") || path.startsWith("/")) {
                path = path.substring(1);
            }
        } catch (Exception e) {
            System.out.println("out off here!!");
        }
        Map<String, String> pathMap = parsePath(path);
        String fileDir = null;
        if (pathMap.get("fileDir") != null) {
            fileDir = pathMap.get("fileDir").replace("\\", "/");
        }
        ObsClient obsClient = getObsClient();
        PutObjectResult putObjectResult;
        try {
            createBucketName(fileDir, obsClient);
            putObjectResult = obsClient.putObject(properties.getBucketName(), path, new ByteArrayInputStream(bFile));
        } catch (Exception e) {
            throw e;
        } finally {
            obsClient.close();
        }
        return putObjectResult.getObjectKey();
    }

    @Override
    public String fileDownLoadByKey(String fileKey, Integer lastMinute) {
        //todo 待实现
        return "";
    }

    @Override
    public boolean fileExist(String fileKey) {
        //todo 待实现
        return true;
    }

    @Override
    public void fileDelete(String fileKey) {
        fileKey = fileKey.replace("\\", "/");
        ObsClient obsClient = getObsClient();
        DeleteObjectResult deleteObjectResult = obsClient.deleteObject(properties.getBucketName(), fileKey);
    }

    private void createBucketName(String fileDir, ObsClient obsClient) {
        boolean existFolder = obsClient.doesObjectExist(properties.getBucketName(), fileDir + "/");
        if (!existFolder) {
            NewFolderRequest newFolderRequest = new NewFolderRequest();
            newFolderRequest.setBucketName(properties.getBucketName());
            newFolderRequest.setObjectKey(fileDir);
            obsClient.newFolder(newFolderRequest);
        }
    }

    protected Map<String, String> parsePath(String path) {
        File f = new File(path);
        Map<String, String> pathMap = new HashMap<String, String>();
        pathMap.put("fileDir", f.getParent());
        pathMap.put("fileName", f.getName());
        if (!path.contains(".")) {
            path = path + ".jpg";
        }
        pathMap.put("fileType", FileUtils.getContentType(path.substring(path.lastIndexOf("."))));
        return pathMap;
    }

}
