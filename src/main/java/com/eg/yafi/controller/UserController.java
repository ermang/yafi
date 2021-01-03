package com.eg.yafi.controller;

import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.service.UserCommandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserCommandService userCommandService;

    public UserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping()
    public void createUser(@RequestBody @Valid CreateUser createUser){
        userCommandService.createUser(createUser);
    }
}
