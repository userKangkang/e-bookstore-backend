package com.example.ebookbackend.dao.impl;


import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.DTO.UserSignupDTO;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.domain.UserAuth;
import com.example.ebookbackend.repo.OrderBookRepository;
import com.example.ebookbackend.repo.UserAuthRepository;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderBookRepository orderBookRepository;

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public UserAuth getAuthByUsername(String username, String password) {
        return userAuthRepository.findUserAuthByUsernameAndPassword(username, password);
    }

    @Override
    public User insertNewUser(UserSignupDTO user) {
        UserAuth auth = new UserAuth();
        auth.setUsername(user.getUsername());
        auth.setPassword(user.getPassword());
        auth.setValid(1);
        userAuthRepository.save(auth);
        User usr = new User();
        usr.setUsername(user.getUsername());
        usr.setEmail(user.getEmail());
        usr.setAvatar(user.getAvatar());
        usr.setBalance(0);
        usr.setState(1);
        usr.setSignature(user.getSignature());
        usr.setHobby(user.getHobby());
        usr.setIdentity(0);
        return userRepository.save(usr);
    }

    @Override
    public Integer updateProfile(User user) {
        userAuthRepository.updateUsername(user.getUsername(), user.getId());
        return userRepository.updateUserProfile(
                user.getAvatar(), user.getHobby(), user.getSignature(), user.getId());
    }

    @Override
    public User getUserByUsername(String username) {
        Integer id = userAuthRepository.getIdByUsername(username);
        if(id == null) {
            return null;
        }
        User user = userRepository.getById(id);
        user.setUsername(username);
        return user;
    }

    @Override
    public int getBalanceById(Integer id) {
        return userRepository.getBalanceById(id);
    }

    @Override
    public List<StatisticsUserDTOInterface> getUserStatInfo(Integer uid, String startTime, String endTime) {
        return orderBookRepository.getUserStat(uid, startTime, endTime);
    }
}
