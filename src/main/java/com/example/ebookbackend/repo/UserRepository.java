package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.User;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<User, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update User u set u.avatar = :avatar, u.hobby = :hobby, u.signature = :signature where u.id = :id")
    int updateUserProfile(@Param("avatar") String avatar,

                           @Param("hobby") String hobby, @Param("signature") String signature, @Param("id") Integer id);


    @Modifying
    @Transactional
    @Query(value = "update User u set u.balance = u.balance - :money where u.id = :id")
    void reduceBalance(@Param("money") Integer money, @Param("id") Integer id);

    @Query(value = "select u.balance from User u where u.id = :id")
    int getBalanceById(Integer id);

}
