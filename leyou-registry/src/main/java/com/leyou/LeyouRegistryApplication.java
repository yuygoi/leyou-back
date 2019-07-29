package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 叶俊晖
 * @date 2019/7/22 0022 10:50
 */
@SpringBootApplication
@EnableEurekaServer
public class LeyouRegistryApplication {
    
    public static void main(String[] args){
        SpringApplication.run(LeyouRegistryApplication.class);
    }
}
