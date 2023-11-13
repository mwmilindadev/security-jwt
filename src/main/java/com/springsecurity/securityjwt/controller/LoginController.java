package com.springsecurity.securityjwt.controller;

import com.springsecurity.securityjwt.dto.LoginRequest;
import com.springsecurity.securityjwt.dto.LoginResponce;
import com.springsecurity.securityjwt.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {

    @Autowired
    private JwtService jwtService;
    @PostMapping({"/authentication"})

    public LoginResponce createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception{
        System.out.println("Test");
        return jwtService.createJwtToken(loginRequest);

    }
}
