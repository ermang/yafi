package com.eg.yafi.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import com.eg.yafi.entity.Thread;

public interface ThreadRepo extends JpaRepository<Thread, Long> {

}
