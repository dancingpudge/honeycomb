# 立方高杆中间存储组件

## 快速使用

### 依赖引入

    由于并非所有项目都需要使用，故存储组件需要使用时独立映入。各个项目的存储需求可能各不相同，故不做为独立服务提供。

maven

 ````maven
<!--honeycomb-storage-starter-->
<dependency>
    <groupId>com.pudge.honeycomb</groupId>
    <artifactId>honeycomb-storage-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
 ````

### 使用

- 配置

  ````yml
  honeycomb:
      #存储
      storage: 
         #存储方案根据配置自动选取，一个项目只支持一种
         oss:  
            prefix: 
            accessKeyId: 
            accessKeySecret: 
            bucketName:
            endpoint: 
         obs:
            accessKey:
            secretKey:
            endPoint:
            bucketName:
            imageExpire:
            connectionTimeout:
  ````
- 调用

  注入ReformerStorageUtil即可

  实现了接口：com.honeycomb.storage.service.StorageUtils

## 第三方存储支持

### oss 3.10.2

[对象存储 OSS ](https://help.aliyun.com/document_detail/32008.html)
  
### obs 3.19.7

[华为云 Java 软件开发工具包](https://github.com/huaweicloud/huaweicloud-sdk-java-v3/blob/master/README_CN.md)