package com.moti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping({"/","/index","/index.html"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    /**
     * @return 跳转到classpath:/templates/success.html
     */
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("who","moti");
        map.put("content","<h1>我是H1里面的内容</h1>");
        map.put("users", Arrays.asList("张三", "李四", "王五"));
        return "success";
    }
}
