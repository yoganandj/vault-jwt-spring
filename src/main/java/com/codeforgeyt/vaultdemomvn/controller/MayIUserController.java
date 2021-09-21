package com.codeforgeyt.vaultdemomvn.controller;

import com.codeforgeyt.vaultdemomvn.model.UserLogin;
import com.codeforgeyt.vaultdemomvn.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
//@RequestMapping("/mayIUser")
public class MayIUserController {

    @Autowired
    private SecurityServiceImpl securityService;

    @RequestMapping("/mayIUser/tokens/user")
    public Map<String, Object> generateJWTTokenForUser(@RequestHeader(value = "userId") final String userId,
                                                       @RequestHeader(value = "tenantId") final String tenantId,
                                                       HttpServletResponse response){

        System.out.println("generateJWTTokenForUser ---------------");
        UserLogin userLogin = new UserLogin();
        userLogin.setUserId(userId);
        userLogin.setTenantId(tenantId);
        return securityService.login(userLogin, response);
    }
}
