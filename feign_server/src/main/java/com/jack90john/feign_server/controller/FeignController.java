package com.jack90john.feign_server.controller;

import com.jack90john.feign_server.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * Designer: jack
 * Date: 2019-01-22
 * Version: 1.0.0
 */

@RestController
public class FeignController {

    @Autowired
    private FeignService feignService;

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String test(@PathVariable String id){
        return feignService.hello(id);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test2(@RequestParam("test") String id){
        return feignService.hello(id);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return feignService.hello();
    }

}
