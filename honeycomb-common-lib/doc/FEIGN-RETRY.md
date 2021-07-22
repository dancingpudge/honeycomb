# 前言

## 组件

### feign

Feign是一个声明式的web service客户端，它使得编写web
service客户端更为容易。创建接口，为接口添加注解，即可使用Feign。Feign可以使用Feign注解或者JAX-RS注解，还支持热插拔的编码器和解码器。Spring Cloud为Feign添加了Spring
MVC的注解支持，并整合了Ribbon和Eureka来为使用Feign时提供负载均衡。

feign源码的github地址：https://github.com/OpenFeign/feign

### ribbon

Nacos默认已经集成了Ribbon

Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。简单的说，就是在配置文件中列出Load
Balancer后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。简单地说，Ribbon是一个客户端负载均衡器。

Ribbon工作时分为两步：第一步先选择 Eureka Server,
它优先选择在同一个Zone且负载较少的Server；第二步再根据用户指定的策略，在从Server取到的服务注册列表中选择一个地址。其中Ribbon提供了多种策略，例如轮询、随机、根据响应时间加权等。

ribbon源码的github地址：https://github.com/Netflix/ribbon

### hystrix

Hystrix熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。在Spring Cloud Hystrix中实现了线程隔离、断路器等一系列的服务保护功能。它也是基于Netflix的开源框架
Hystrix实现的，该框架目标在于通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备了服务降级、服务熔断、线程隔离、请求缓存、请求合并以及服务监控等强大功能。

Hystrix源码的github地址：https://github.com/Netflix/hystrix

###   

----

# 功能

* 注意：使用此功能</br>
  1、需要重试的需要满足"幂等"！！！ </br>
  2、次方法按照例子只在hystrix熔断后才会记录，正常重试时不记录。</br>
  3、备份的数据可能有冗余，但是重试时会去重</br>
  </br>
* 存储：提供redis和file两种方式，来进行需重试方法和参数的保存。redis按照key来分割，文件保存按照日期分割。隔天的数据重试次数默认比当天的频率低。</br>
* 重试：从存储的文件中，获取需重试信息，结合定时任务来重试。不开启定时任务，则只记录不重试

----

# 使用

## 配置

````yaml
#ribbon的使用
ribbon:
  #是否集成Ribbon
  nacos.enabled: true
  # 等待请求响应的超时时间. 单位：ms
  ReadTimeout: 5000
  # 连接超时时间. 单位：ms
  ConnectTimeout: 1000
  # 是否对所有请求进行失败重试, 设置为 false, 让feign只针对Get请求进行重试.
  OkToRetryOnAllOperations: true
  # 同一个服务实例的重试次数 (excluding the first try)
  MaxAutoRetries: 1
  # 不同服务实例的重试次数(excluding the first server)
  MaxAutoRetriesNextServer: 1

#集成熔断机制
feign:
  hystrix:
    enabled: true
#无响应时触发熔断时长阈值
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 6000

#熔断后数据重试组件开启，每分钟重试1次，隔天的频率减半，需开启定时任务
honeycomb:
  feign.retry:
    #重试缓存方案（目前只支持file，redis非必填）
    storageMode: file
    #制定文件存储路径（一下为默认值，非必填）
    fileDir: honeycomb-feign-retry
````

## 自定义callback

````Java

@Slf4j
@Component
public class Demo2ClientFallBack implements Demo2Client {
    @Resource
    FeignRetryHandler feignRetryHandler;

    @Override
    public StandardResultVO<Demo2VO> demoTest(Demo2DTO demoDto) {
        log.info("记录熔断信息");
        feignRetryHandler.recorder(Demo2Client.class.getName(), Thread.currentThread().getStackTrace()[1].getMethodName(), demoDto);
        return StandardResultVO.hystrixResult();
    }

    @Override
    public StandardResultVO<Integer> save(DemoAccountDTO demoAccountDTO) {
        return null;
    }

    @Override
    public StandardPageResultVO getPage(PageForm pageForm) {
        return null;
    }
}
````

## 日志追踪整合

接口调用方看情况开启

````yaml
#feign调用情况日志追踪
logging:
  level:
    #feign日志以什么级别监控哪个接口
    Demo2Client: debug
````
