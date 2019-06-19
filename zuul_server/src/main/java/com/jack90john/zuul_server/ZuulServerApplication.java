package com.jack90john.zuul_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableOAuth2Sso
@EnableZuulProxy
@SpringCloudApplication
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

//    @Bean
//    public AccessFilter accessFilter() {
//        return new AccessFilter();
//    }

}

