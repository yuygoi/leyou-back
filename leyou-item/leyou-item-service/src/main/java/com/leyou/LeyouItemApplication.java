package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 11:27
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")
public class LeyouItemApplication {
    
    public static void main(String[] args){
        SpringApplication.run(LeyouItemApplication.class);
    }
}
