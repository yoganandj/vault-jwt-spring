package com.codeforgeyt.vaultdemomvn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplication {

    @RequestMapping("/")
    String hello(){
        return "Hello Spring Boot JWT with Vault";
    }

}
