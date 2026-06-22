package com.eg.yafi.controller;

import com.eg.yafi.config.CustomUserDetailsService;
import com.eg.yafi.config.JwtUtil;
import com.eg.yafi.req.CreateUserReq;
import com.eg.yafi.req.LoginReq;
import com.eg.yafi.service.UserCommandService;
import com.eg.yafi.servicereq.CreateUserServiceReq;
import com.eg.yafi.util.Req2ServiceReq;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserCommandService userCommandService;


    private final AuthenticationManager authManager;
    private final JwtUtil jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final Req2ServiceReq req2ServiceReq;

    public UserController(UserCommandService userCommandService, AuthenticationManager authManager, JwtUtil jwtService,
                          CustomUserDetailsService userDetailsService, Req2ServiceReq req2ServiceReq) {
        this.userCommandService = userCommandService;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.req2ServiceReq = req2ServiceReq;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginReq request) {
        System.out.println("RAW PASSWORD = [" + request.password + "]");
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username, request.password));

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        return jwtService.generateToken(userDetails);
    }
    @PostMapping()
    public void createUser(@RequestBody @Valid CreateUserReq createUserReq) {
        CreateUserServiceReq serviceReq = req2ServiceReq.createUserReq2CreateUserServiceReq(createUserReq);
        userCommandService.createUser(serviceReq);
    }
}
