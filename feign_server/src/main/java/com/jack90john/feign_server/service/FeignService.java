package com.jack90john.feign_server.service;

import com.jack90john.feign_server.service.impl.Fallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Designer: jack
 * Date: 2019-01-21
 * Version: 1.0.0
 */

@FeignClient(value = "test", fallback = Fallback.class)
public interface FeignService {

    @RequestMapping("/test")
    String hello(@RequestParam("test") String id);

    @RequestMapping("/hello")
    String hello();

}
