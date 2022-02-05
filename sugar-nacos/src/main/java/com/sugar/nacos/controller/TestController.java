package com.sugar.nacos.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${sugar.name}")
    private String MyName;
    @Value("${sugar.url}")
    private String Myurl;
    @Value("${sugar.github}")
    private String MyGitHub;

    @GetMapping("/hello")
    public String hello() {
        return "我是：" + Myurl + "\n" + "我的博客：" + Myurl + "\n" + "我的项目：" + MyGitHub;
    }
}
