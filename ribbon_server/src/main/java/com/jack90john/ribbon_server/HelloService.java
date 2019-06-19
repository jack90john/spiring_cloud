package com.jack90john.ribbon_server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

/**
 * Description:
 * Designer: jack
 * Date: 2018/8/2
 * Version: 1.0.0
 */

@Slf4j
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public Future<String> helloService(){
        AsyncResult<String> asyncResult = new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://TEST/eureka", String.class);

            }
        };
        log.info(asyncResult.getClass().getName());
        return asyncResult;
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String hello(){
        return restTemplate.getForEntity("http://TEST/hello", String.class).getBody();
    }

    public String helloFallBack() throws TimeoutException {
        throw new TimeoutException();
    }
}
