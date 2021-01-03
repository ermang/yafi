package com.eg.yafi.service;

import com.eg.yafi.dto.in.CreateUser;
import com.eg.yafi.entity.AppUser;
import com.eg.yafi.repo.AppUserRepo;
import com.eg.yafi.util.Dto2Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserCommandService {
    private final AppUserRepo appUserRepo;
    private final Dto2Entity dto2Entity;

    public UserCommandService(AppUserRepo appUserRepo, Dto2Entity dto2Entity) {
        this.appUserRepo = appUserRepo;
        this.dto2Entity = dto2Entity;
    }

    public void createUser(CreateUser createUser) {
        AppUser appUser = dto2Entity.createUser2AppUser(createUser);

        appUserRepo.save(appUser);
    }
}
