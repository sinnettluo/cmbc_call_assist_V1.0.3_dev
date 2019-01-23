package com.guiji;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import com.guiji.component.result.EnableAutoResultPack;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ty on 2018/10/18.
 */
@SpringBootApplication
@EnableDiscoveryClient
// @MapperScan("com.guiji.user.dao")
@EnableSwagger2
@EnableAutoResultPack
@EnableFeignClients // 启用feign
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}