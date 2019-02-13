package com.guiji.callcenter.fsmanager;

import com.guiji.component.result.EnableAutoResultPack;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
//@MapperScan("com.guiji.*.dao")
@EnableAutoResultPack
@SpringBootApplication(scanBasePackages = "com.guiji")
public class FsmanagerApplication {
    public static void main(String[] args){
        SpringApplication.run(FsmanagerApplication.class,args);
    }
}
