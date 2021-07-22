package com.honeycomb.storage.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description: com.honeycomb.storage.service <br>
 * @date: 2021/2/20 4:21 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public interface StorageUtils {

    /**
     * 文件上传，返回存储地址
     * oss: OssKey
     * local: 文件路径
     *
     * @param fileKey     文件存储位置，oss则代表OssKey，本地存储则为文件路径+文件名
     * @param inputStream 文件流
     * @return OssKey
     * @throws IOException
     */
    String uploadFile(String fileKey, InputStream inputStream) throws IOException;

    /**
     * 文件上传，返回存储地址
     * oss: OssKey
     * local: 文件路径
     *
     * @param fileKey     文件存储位置，oss则代表OssKey，本地存储则为文件路径+文件名
     * @param inputStream 文件流
     * @return OssKey
     * @throws IOException
     */
    void uploadFileByAbsolutePath(String fileKey, InputStream inputStream) throws IOException;

    /**
     * 文件上传，返回存储地址
     *
     * @param bFile 文件字节流
     * @param path  文件全路径
     * @return
     * @throws Exception
     */
    String uploadFile(byte[] bFile, String path) throws Exception;

    /**
     * 获取文件下载地址
     *
     * @param fileKey    文件存储位置，oss则代表OssKey，本地存储则为文件路径+文件名
     * @param lastMinute 有效期（分钟）
     * @return 文件路径
     */
    String fileDownLoadByKey(String fileKey, Integer lastMinute);

    /**
     * 检查文件是否存在
     *
     * @param fileKey 文件存储位置，oss则代表OssKey，本地存储则为文件路径+文件名
     * @return
     */
    boolean fileExist(String fileKey);

    /**
     * 文件删除
     *
     * @param fileKey 文件存储位置，oss则代表OssKey，本地存储则为文件路径+文件名
     */
    void fileDelete(String fileKey);

}
