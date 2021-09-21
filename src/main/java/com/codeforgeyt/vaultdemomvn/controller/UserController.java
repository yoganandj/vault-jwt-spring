package com.codeforgeyt.vaultdemomvn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/users")
    String getUsers(){
        return "{\n" +
                "     \"user\": [{\n" +
                "         \"firstname\": \"Richard\",\n" +
                "         \"lastname\": \"Feynman\"\n" +
                "     }, {\n" +
                "         \"firstname\": \"Marie\",\n" +
                "         \"lastname\": \"Curie\"\n" +
                "     }]\n" +
                " }";
    }

}
