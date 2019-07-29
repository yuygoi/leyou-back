package com.leyou.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author 叶俊晖
 * @date 2019/7/23 0023 9:02
 */
@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨域的域名，如果要携带cookie，不能写*，
        //*代表所有域名都可以访问
        configuration.addAllowedOrigin("http://manage.leyou.com");
        configuration.setAllowCredentials(true);
        //设置允许的请求方法类型：GET ，POST 。。。
        configuration.addAllowedMethod("*");
        //允许携带任何头信息
        configuration.addAllowedHeader("*");

        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource =
                new UrlBasedCorsConfigurationSource();
        //注册跨域请求拦截路径
        configurationSource.registerCorsConfiguration("/**",configuration);

        //返回corsFilter实例，参数：cors配置源对象
        return new CorsFilter(configurationSource);
    }
}
