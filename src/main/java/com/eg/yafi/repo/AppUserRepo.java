package com.eg.yafi.repo;


import com.eg.yafi.dto.ReadUser;
import com.eg.yafi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findOneByUsername(String username);
//    @Query(value = "SELECT new com.eg.yafi.dto.ReadThread(t.id AS id, t.topic.id AS topicId, t.content AS content, t.appUser.username AS username)" +
//            "    FROM Thread t" +
//            "    WHERE t.id = :threadId")
//public ReadUser(Long id, String username, String role, boolean enabled)
    @Query(value = "SELECT new com.eg.yafi.dto.ReadUser(a.id AS id, a.username AS username, a.password AS password," +
                    "   a.role AS role, a.enabled AS enabled)" +
                    "       FROM AppUser a" +
                    "       WHERE a.username = :username")
    ReadUser findOneByUsernameRO(@Param("username")String username);
}
