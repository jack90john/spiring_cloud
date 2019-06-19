package com.jack90john.discovery_client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Description:
 * Designer: jack
 * Date: 2017/12/28
 * Version: 1.0.0
 */

@Slf4j
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/eureka", method = RequestMethod.GET)
    public String eureka() {
//        ServiceInstance instance = client.getLocalServiceInstance();
//        log.info("/test/eureka, host: {}, service_id: {}", instance.getHost(), instance.getServiceId());
        log.info("test!");
        return "hello world";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam("test") String test) {
        log.info(test);
//        ServiceInstance instance = client.getLocalServiceInstance();
//        log.info("/test/eureka, host: {}, service_id: {}", instance.getHost(), instance.getServiceId());
        return "hello world";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws InterruptedException {
        HttpServletRequest client = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime: {}", sleepTime);
        Thread.sleep(sleepTime);
        log.info("/hello, host: {}, service_id: {}", client.getRemoteHost(), client.getServerName() );
        return "HELLO WORLD!";
    }
}
