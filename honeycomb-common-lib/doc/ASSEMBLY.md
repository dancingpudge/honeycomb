# 功能模块
* 参数强校验spring-cloud-starter-openfeign，javax.validation spring-boot-starter-web包里面有hibernate-validator包，不需要引用hibernate
  validator依赖。 2.4.0版本后移除需要单独引用 各项目自已引入（不做版本限制）

  ````maven
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.3.2.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
  
    <properties>
        <spring.openfeign.version>2.1.1.RELEASE</spring.openfeign.version>
    </properties>
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
        <version>${spring.openfeign.version}</version>
    </dependency>
    <!--版本由spring-boot-starter-parent版本限制，此包包含javax.validation-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
   ````  
* 参考文档 http://hibernate.org/validator/documentation/

* redis的使用 通过honeycomb-common-lib包引入,版本由springboot管理。集成Redisson3.11.1
  ````maven
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.redisson</groupId>
        <artifactId>redisson</artifactId>
    </dependency>
    ````
  通过spring.redis.enable:true 来开启，其余配置保持不变 配置文件请参看demo 项目的yaml
    ````yaml
      spring: 
        redis:
            #无此属性或者为false则不开启redis
            enable: true
            host: localhost
            database: 3
            port: 6379
            password: 123456
        jedis:
            pool:
              max-active: 30
              max-wait: -1
              max-idle: 8
              min-idle: 0
    ````
  分布式锁（非集群）com.honeycomb.common.redis.RedisLockUtil 
  基于Redisson分布式锁线程创建工具类com.honeycomb.common.redis.RedisLockUtils

* [redission说明 ](REDISSION-README.md)
  
* kafka的使用（做了消息基本信息格式的封装） 通过honeycomb-common-lib包引入,版本由springboot管理。
  ````maven
      <dependency>
          <groupId>org.springframework.kafka</groupId>
          <artifactId>spring-kafka</artifactId>
      </dependency>
    ````
  kafka发送 参考demo-svc中： KafkaProducer kafka消费     
  参考demo-svc中： DemoKafkaConsumer

  配置文件请参看demo 项目的yaml
    ````yaml
    spring:
       ###########【Kafka集群】###########
      kafka:
        bootstrap-servers: localhost:9092
        ###########【初始化生产者配置】###########
        # 重试次数
        producer:
          # 发送的topic
          topic-name: demo
          retries: 0
          # 应答级别:多少个分区副本备份完成时向生产者发送ack确认(可选0、1、all/-1)
          acks: 1
          # 批量大小
          batch-size: 16384
          # 提交延时
          properties.linger.ms: 0
          # 当生产端积累的消息达到batch-size或接收到消息linger.ms后,生产者就会将消息提交给kafka
          # linger.ms为0表示每接收到一条消息就提交给kafka,这时候batch-size其实就没用了
          # 生产端缓冲区大小
          buffer-memory: 33554432
          # Kafka提供的序列化和反序列化类
          key-serializer: org.apache.kafka.common.serialization.StringSerializer
          value-serializer: org.apache.kafka.common.serialization.StringSerializer
        ###########【初始化消费者配置】###########
        # 默认的消费组ID
        consumer:
          # 消费者topic,可逗号分隔
          topic-name: demo
          # 是否自动提交offset
          enable-auto-commit: true
          # 提交offset延时(接收到消息后多久提交offset)
          auto.commit.interval.ms: 1000
          # 批量消费每次最多消费多少条消息
          max-poll-records: 50
          # 当kafka中没有初始offset或offset超出范围时将自动重置offset
          # earliest:重置为分区中最小的offset;
          # latest:重置为分区中最新的offset(消费分区中新产生的数据);
          # none:只要有一个分区不存在已提交的offset,就抛出异常;
          auto-offset-reset: latest
          # Kafka提供的序列化和反序列化类
          value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
          key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
          properties:
            group.id: demoConsumerGroup
            # 消费会话超时时间(超过这个时间consumer没有发送心跳,就会触发rebalance操作)
            session.timeout.ms: 120000
            # 消费请求超时时间
            request.timeout.ms: 180000
        listener:
          # 消费端监听的topic不存在时，项目启动会报错(关掉)
          missing-topics-fatal: false
          # 设置批量消费
          #type: batch
    ````

* springfox-swagger3 通过honeycomb-common-lib包引入，并进行统一版本控制目前 查看地址：启动demo项目访问
  [此项目swagger文档](http://localhost:8081/honeycomb-demo/doc.html#/)
  ````maven
      <properties>
           <swagger3.version>3.0.0</swagger3.version>
      </properties>
  
      <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>${swagger3.version}</version>
      </dependency>
   ````

  在需要使用的应用中添加maven依赖

  ````maven
      <!--swagger3-->
      <dependency>
          <groupId>com.github.xiaoymin</groupId>
          <artifactId>knife4j-aggregation-spring-boot-starter</artifactId>
          <!--在引用时请在maven中央仓库搜索Knife4jAggregation最新版本号-->
          <version>${swagger.version}</version>
      </dependency>
  ````


* 参考文档

[swagger 官网](https://swagger.io/)
[springfox 官网（目前无法访问）](http://springfox.github.io)
[springfox 源码](https://github.com/springfox/springfox)

[插件源码](https://gitee.com/xiaoym/knife4j)

[版本说明](https://doc.xiaominfo.com/knife4j/documentation/changelog.html)

* 日志统一处理 使用log4j2,添加对应maven依赖，并加入log4j2.xml即可
  ````maven
    <!--全局排除spring-boot-starter-logging内的所有依赖-->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
          <exclusions>
              <exclusion>
                  <groupId>*</groupId>
                  <artifactId>*</artifactId>
              </exclusion>
          </exclusions>
      </dependency>
      <!-- 配置 log4j2 -->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-log4j2</artifactId>
      </dependency>
      <dependency>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
          <version>1.2.16</version>
          <scope>compile</scope>
      </dependency>
  ````  
* 异常统一处理（可以交由产品统一确认修改错误文案） 通过honeycomb-common-lib包引入，启动类@EnableReformerAll或@EnableReformerApiExp来开启该功能。
  对应项目中需要配置文件api-error.properties，各项目独立维护业务异常信息和错误码

* 请求日志统一拦截 通过honeycomb-common-lib包引入，启动类@EnableReformerAll或@EnableReformerHttpLog来开启该功能。 对表单和json格式的接口都会打印日志 eg:
    ````log
    访问者ip==>0:0:0:0:0:0:0:1 Path==>/honeycomb-demo/demo/api-exception parameters==>{"参数实例":["fafasfa"]}
    访问者ip==>0:0:0:0:0:0:0:1 Path==>/honeycomb-demo/demo/accept body==>{  "call_back_type": "string",  "confirm_time": "string",  "createTime": "2020-12-01T11:48:23.100Z",  "is_success": true,  "mobile": "string",  "username": "string"} parameters==>{}
    ````
* 异步线程创建 ContextCopyingDecorator
  [文档](https://stackoverflow.com/questions/23732089/how-to-enable-request-scope-in-async-task-executor)

* 异步线程池简单配置 通过honeycomb-common-lib包引入，启动类@EnableReformerAll或@EnableReformerAsync来开启该功能。 只做简单配置（按计算密集型来配置）
     ````java
    public class AsyncConfig {
        private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2 + 1;
    
        private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    
        private static final int QUEUE_CAPACITY = 1000;
    
        private static final int KEEP_ALIVE_SECONDS = 200;
    
        private static final int AWAIT_TERMINATION_SECONDS = 60;
    
        private static final String THREAD_NAME_PREFIX = "honeycomb-thread-pool-";
    
        @Bean("asyncTaskExecutor")
        public AsyncTaskExecutor asyncTaskExecutor() {
            ThreadPoolTaskExecutor asyncTaskExecutor = new ThreadPoolTaskExecutor();
            asyncTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
            asyncTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
            asyncTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
            asyncTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
            asyncTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
            asyncTaskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS);
            // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
            asyncTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            //调度器shutdown被调用时等待当前被调度的任务完成
            asyncTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            asyncTaskExecutor.initialize();
            return asyncTaskExecutor;
        }
    
    }
   ````
* 对象映射工具（PO和DTO对象转化工具）

[Cglib BeanCopier示例](https://github.com/mapstruct/mapstruct-examples)

通过honeycomb-common-lib包引入，使用请参看文中BeanCopierUtil使用样例
 ````maven
<!--cglib-->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib-nodep</artifactId>
    <version>2.2.2</version>
</dependency>
 ````
使用 BeanCopierUtil
````Java
  //A 对象初始化拷贝
  EvidencePO evidencePO = BeanCopierUtil.initCopy(pictureRecordDTO,EvidencePO.class);
  
  //B 对象属性拷贝
  EvidencePO evidencePO;
  //....属性赋值
  //注意只要字段和类型相匹配，原有值会被覆盖。参看 BeanCopierUtilTest    
  EvidencePO evidencePO = BeanCopierUtil.initCopy(pictureRecordDTO,evidencePO);
  
  //C 集合拷贝
  List parkingSpaceTypeVo = BeanCopierUtil.copyList(list, ParkingSpaceTypeVO.class);

````
  

  

  