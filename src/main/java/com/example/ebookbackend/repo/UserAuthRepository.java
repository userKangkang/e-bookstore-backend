package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    public UserAuth findUserAuthByUsernameAndPassword(String username, String password);

    @Modifying
    @Transactional
    @Query(value = "update UserAuth u set u.valid = 0 where u.id = :id")
    void disableUser(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update UserAuth u set u.valid = 1 where u.id = :id")
    void enableUser(@Param("id") int id);

    @Query(value = "select ua.valid as valid from UserAuth ua where ua.username = :username")
    Integer getValidByUsername(@Param("username") String username);

    @Query(value = "select ua.id as id from UserAuth ua where ua.username = :username")
    Integer getIdByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "update UserAuth ua set ua.username = :username where ua.id = :id")
    void updateUsername(@Param("username") String username, @Param("id") Integer id);
}
