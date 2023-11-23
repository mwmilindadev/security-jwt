package com.springsecurity.securityjwt.controller;

import com.springsecurity.securityjwt.entity.User;
import com.springsecurity.securityjwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"register-new-user"})
    public User createNewUser(@RequestBody User user){

        return userService.registerUser(user);

    }
    @PostConstruct
    public void initAdminAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping({"/for-admin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This is admin";
    }

    @GetMapping({"/for-user"})
    //@PreAuthorize("hasRole('User')")
    @PreAuthorize("hasAnyRole('User','Admin')")//can login to user and admin
    public String forUser(){
        return "This is user";
    }
}
