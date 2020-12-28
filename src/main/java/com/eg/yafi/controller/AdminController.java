package com.eg.yafi.controller;

import com.eg.yafi.service.AdminCommandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminCommandService adminCommandService;

    public AdminController(AdminCommandService adminCommandService) {
        this.adminCommandService = adminCommandService;
    }


}
