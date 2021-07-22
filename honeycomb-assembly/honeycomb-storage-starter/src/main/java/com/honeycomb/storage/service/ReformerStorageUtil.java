package com.honeycomb.storage.service;

import com.honeycomb.storage.service.impl.ObsStorageUtils;
import com.honeycomb.storage.service.impl.OssStorageUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * @description: com.honeycomb.storage.config <br>
 * @date: 2021/2/22 4:30 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
@Component
public class ReformerStorageUtil implements StorageUtils {

    @Autowired
    List<StorageUtils> storageUtils;

    private StorageUtils storageClient;

    @PostConstruct
    void init() throws BeansException {
        storageUtils.forEach(s -> {
            if (s instanceof OssStorageUtils && Optional.ofNullable(((OssStorageUtils) s).properties.getBucketName()).isPresent()) {
                storageClient = s;
                return;
            }
            if (s instanceof ObsStorageUtils && Optional.ofNullable(((ObsStorageUtils) s).properties.getBucketName()).isPresent()) {
                storageClient = s;
                return;
            }
        });
    }

    @Override
    public String uploadFile(String fileKey, InputStream inputStream) throws IOException {
        return storageClient.uploadFile(fileKey, inputStream);
    }

    @Override
    public void uploadFileByAbsolutePath(String fileKey, InputStream inputStream) throws IOException {
        storageClient.uploadFileByAbsolutePath(fileKey, inputStream);
    }

    @Override
    public String uploadFile(byte[] bFile, String path) throws Exception {
        return storageClient.uploadFile(bFile, path);
    }

    @Override
    public String fileDownLoadByKey(String fileKey, Integer lastMinute) {
        return storageClient.fileDownLoadByKey(fileKey, lastMinute);
    }

    @Override
    public boolean fileExist(String fileKey) {
        return storageClient.fileExist(fileKey);
    }

    @Override
    public void fileDelete(String fileKey) {
        storageClient.fileDelete(fileKey);
    }
}
