package com.eg.yafi.service;

import com.eg.yafi.entity.AppUser;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.servicereq.CreateUserServiceReq;
import com.eg.yafi.util.Constant;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserCommandService {
    private final AppUserRepo appUserRepo;
    private final Dto2Entity dto2Entity;
    private final PasswordEncoder passwordEncoder;

    public UserCommandService(AppUserRepo appUserRepo, Dto2Entity dto2Entity, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.dto2Entity = dto2Entity;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserServiceReq createUserServiceReq) {

        AppUser appUser = new AppUser();
        appUser.setUsername(createUserServiceReq.username);
        appUser.setPassword(passwordEncoder.encode(createUserServiceReq.password));
        appUser.setRole(Constant.ROLE_USER);
        appUser.setEnabled(true);

        appUserRepo.save(appUser);
    }
}
