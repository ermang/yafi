package com.eg.yafi.repo;


import com.eg.yafi.entity.AppUser;
import com.eg.yafi.entity.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

}
