package com.example.ebookbackend.dao.impl;


import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Override
    public String getPasswordByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user.getPassword();
    }

    @Override
    public User insertNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Integer updateProfile(User user) {
        return userRepository.updateUserProfile(user.getUsername(),
                user.getAvatar(), user.getHobby(), user.getSignature(), user.getId());
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
