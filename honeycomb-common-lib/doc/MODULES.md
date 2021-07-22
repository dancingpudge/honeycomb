# 项目说明

此项目为honeycomb快速搭建基于springboot技术栈的脚手架模板

|  模块   | 单元  | 功能  |
|  ----  | ----  |----  |
| 核心组件（工具类）  |  | |
|   |  honeycomb-common-lib |公共组件 |
|   |  honeycomb-hoenycomb-dependencies |公共组件版本依赖管理 |
|||||
| 模板（快速开发样例项目）  |  | |
|honeycomb-demo| 独立的项目组| | 
|   |  honeycomb-demo-api   |由对应的svc包提供者统一维护(此处由honeycomb-demo-svc维护) |
|   |  honeycomb-demo-svc   |微服务提供者 |
|   |  honeycomb-demo-portal    |平台端，网关项目(包含C端登录鉴权等统一功能，通过引入honeycomb-X-client调用其他微服务) |
|   |  honeycomb-demo2-api   |模拟其他的微服务接口 |
|   |  honeycomb-demo2-svc   |模拟其他的被调用的微服务 |
|||||
|  可选用组件（后续添加）  |  | |
|   |  honeycomb-ding-talk-starter   |钉钉机器人告警 |
|   |  honeycomb-ding-log-starter   |日志组件（待续。。。。） |
|   |  honeycomb-ding-storage-starter   |钉钉机器人告警 |
|   |  honeycomb-c-sso-starter   |C端登录(暂无，由登录模块提供者提供) |
|   |  honeycomb-b-sso-starter   |B端登录(暂无，由登录模块提供者提供) |
|   |  、、、   |后续增加 |
|||||
|  工具箱  |  | |
|   |  AutoBuildDocFromDB   |mysql db文档生成工具 |

# 微服务治理

[nacos演示样例](http://hoenycombdev.parking24.cn/nacos/)

[nacos官网](https://nacos.io/zh-cn/docs/what-is-nacos.html)

[Nacos Spring Cloud 快速开始](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)

[nacos-discovery使用说明](https://github.com/alibaba/spring-cloud-alibaba/blob/3c6a71cac3c2d85429a0d1d53c587a2716e05831/spring-cloud-alibaba-docs/src/main/asciidoc-zh/nacos-discovery.adoc#%E5%85%B3%E4%BA%8E-nacos-starter-%E6%9B%B4%E5%A4%9A%E7%9A%84%E9%85%8D%E7%BD%AE%E9%A1%B9%E4%BF%A1%E6%81%AF)

[依赖说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

账号：nacos 密码：nacos

# 依赖管理

* 参考文档 通过honeycomb-common-lib包引入，并进行统一版本控制目前

   ````maven
    <!--nacos-->
    <spring.boot.version>2.3.2.RELEASE</spring.boot.version>
    <spring-cloud.version>Hoxton.SR8</spring-cloud.version>
    <spring-cloud-alibaba.version>2.2.3.RELEASE</spring-cloud-alibaba.version>
  
    <dependencyManagement>
      <dependencies>
          <!--SpringCloud-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-dependencies</artifactId>
              <version>${spring-cloud.version}</version>
              <type>pom</type>
              <scope>import</scope>
          </dependency>
          <!--SpringCloud Alibaba-->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-alibaba-dependencies</artifactId>
              <version>${spring-cloud-alibaba.version}</version>
              <type>pom</type>
              <scope>import</scope>
          </dependency>
      </dependencies>
    </dependencyManagement>
     ````

  spring-cloud-starter-alibaba-nacos-config和spring-cloud-starter-alibaba-nacos-discovery需在项目中引入

  ````maven
    <dependencies>
        <!--nacos-config-->
          <dependency>
              <groupId>com.alibaba.cloud</groupId>
              <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
          </dependency>
          <!--nacos-discovery-->
          <dependency>
              <groupId>com.alibaba.cloud</groupId>
              <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
          </dependency>
    </dependencies>
   ```` 
  ````yaml
    spring:
      cloud:
        nacos:
          #注册中心
          discovery:
            server-addr: 192.168.250.4:8848
            namespace: demo
            group: dev
          #配置中心
          config:
            server-addr: 192.168.250.4:8848
            namespace: demo
            group: dev
            #指定yaml格式的配置
            file-extension: yaml
  ````