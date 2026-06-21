package com.eg.yafi.controller;

import com.eg.yafi.config.CustomUserDetailsService;
import com.eg.yafi.config.JwtUtil;
import com.eg.yafi.req.CreateUserReq;
import com.eg.yafi.req.LoginReq;
import com.eg.yafi.service.UserCommandService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserCommandService userCommandService;


    private final AuthenticationManager authManager;
    private final JwtUtil jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(UserCommandService userCommandService, AuthenticationManager authManager, JwtUtil jwtService,
                          CustomUserDetailsService userDetailsService) {
        this.userCommandService = userCommandService;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginReq request) {
        System.out.println("RAW PASSWORD = [" + request.password + "]");
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username, request.password            )        );

        UserDetails user = (UserDetails) auth.getPrincipal();

        return jwtService.generateToken(user);
    }
    @PostMapping()
    public void createUser(@RequestBody @Valid CreateUserReq createUserReq){
        userCommandService.createUser(createUserReq);
    }
}
