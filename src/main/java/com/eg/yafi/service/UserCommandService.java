package com.eg.yafi.service;

import com.eg.yafi.entity.AppUser;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.servicereq.CreateUserServiceReq;
import com.eg.yafi.util.Constant;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserCommandService {
    private final AppUserRepo appUserRepo;
    private final PasswordEncoder passwordEncoder;

    public UserCommandService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserServiceReq createUserServiceReq) {

        AppUser appUser = new AppUser();
        appUser.setUsername(createUserServiceReq.username);
        appUser.setPassword(passwordEncoder.encode(createUserServiceReq.password));
        appUser.setRole(Constant.ROLE_USER);

        appUserRepo.save(appUser);
    }
}
