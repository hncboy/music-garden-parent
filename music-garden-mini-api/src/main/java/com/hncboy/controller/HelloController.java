package com.hncboy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/26
 * Time: 18:07
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Spring Boot";
    }
}
