package com.eg.yafi.repo;

import com.eg.yafi.entity.AppUserThreadLikeRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserThreadLikeRelRepo extends JpaRepository<AppUserThreadLikeRel, Long> {


    void deleteAllByThreadId(long threadId);
}
