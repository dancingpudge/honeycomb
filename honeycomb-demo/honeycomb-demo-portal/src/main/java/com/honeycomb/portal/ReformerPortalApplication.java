package com.honeycomb.portal;

import com.honeycomb.common.annotation.EnableHoneycombAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: honeycomb-parent
 * @description: 启动类，按需开启需要的功能
 * @author: LiuH
 * @create: 2020-12-02 14:55
 **/
@EnableHoneycombAll
@SpringBootApplication
@EnableFeignClients(basePackages = "com.honeycomb")
@ComponentScan(basePackages = "com.honeycomb")
public class ReformerPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReformerPortalApplication.class, args);
    }

}
