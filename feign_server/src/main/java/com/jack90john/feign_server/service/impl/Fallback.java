package com.jack90john.feign_server.service.impl;

import com.jack90john.feign_server.service.FeignService;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Designer: jack
 * Date: 2019-01-23
 * Version: 1.0.0
 */
@Component
public class Fallback implements FeignService {

    @Override
    public String hello(String test) {
        return null;
    }

    @Override
    public String hello() {
        return "time out!!!!!!!!!!";
    }

}
