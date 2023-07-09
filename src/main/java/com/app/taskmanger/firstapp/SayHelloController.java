package com.app.taskmanger.firstapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SayHelloController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){
        logger.debug("inside say hello method");
        return "Hello to Spring MVC";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(){
        return "sayHello";
    }
}
