package com.eg.yafi.repo;

import com.eg.yafi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepo extends JpaRepository<Topic, Long> {

}
