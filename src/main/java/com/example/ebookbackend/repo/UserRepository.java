package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.User;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


    @Modifying
    @Transactional
    @Query(value = "update User u set u.username = :username, u.avatar = :avatar, u.hobby = :hobby, u.signature = :signature where u.id = :id")
    int updateUserProfile(@Param("username") String username,@Param("avatar") String avatar,

                           @Param("hobby") String hobby, @Param("signature") String signature, @Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update User u set u.state = 0 where u.id = :id")
    void disableUser(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.state = 1 where u.id = :id")
    void enableUser(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.balance = u.balance - :money where u.id = :id")
    void reduceBalance(@Param("money") Integer money, @Param("id") Integer id);

    @Query(value = "select u.balance from User u where u.id = :id")
    Integer getBalanceById(Integer id);
}
