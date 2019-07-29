package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 叶俊晖
 * @date 2019/7/24 0024 11:22
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LeyouUploadApplication {
    
    public static void main(String[] args){
        SpringApplication.run(LeyouUploadApplication.class);
    }
}
