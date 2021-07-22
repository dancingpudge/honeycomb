package com.honeycomb.demo2;

import com.honeycomb.common.annotation.EnableHoneycombAll;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: honeycomb-parent
 * @description: 启动类，按需开启需要的功能
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
@EnableHoneycombAll
@EnableFeignClients(basePackages = "com.honeycomb")
@ComponentScan(basePackages = "com.honeycomb")
@MapperScan(basePackages = "com.honeycomb.demo2.entity.dto")
@EnableDiscoveryClient
@SpringBootApplication
public class ReformerDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ReformerDemo2Application.class, args);
    }

}
