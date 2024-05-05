package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


    @Modifying
    @Query(value = "update User u set u.username = :username, u.avatar = :avatar, u.hobby = :hobby, u.signature = :signature where u.id = :id")
    int updateUserProfile(@Param("username") String username,@Param("avatar") String avatar,

                           @Param("hobby") String hobby, @Param("signature") String signature, @Param("id") Integer id);
    @Modifying
    @Query(value = "update User u set u.state = 0 where u.id = :id")
    void disableUser(@Param("id") int id);
}
