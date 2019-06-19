package com.jack90john.ribbon_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * Description:
 * Designer: jack
 * Date: 2017/12/28
 * Version: 1.0.0
 */

@Slf4j
@RestController
public class ribbonController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon", method = RequestMethod.GET)
    public String eureka() throws ExecutionException, InterruptedException {
        return helloService.helloService().getClass().getName();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return helloService.hello();
    }



    @RequestMapping(value = "/ribbon/{test}", method = RequestMethod.GET)
    public String test(@PathVariable("test") String test) throws ExecutionException, InterruptedException {
        return helloService.helloService().get();
    }
}
