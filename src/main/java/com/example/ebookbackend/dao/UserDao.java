package com.example.ebookbackend.dao;

import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.DTO.UserSignupDTO;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.domain.UserAuth;

import java.util.List;

public interface UserDao {
    public UserAuth getAuthByUsername(String username, String password);

    public User insertNewUser(UserSignupDTO user);

    public Integer updateProfile(User user);

    public User getUserByUsername(String username);

    public int getBalanceById(Integer id);

    List<StatisticsUserDTOInterface> getUserStatInfo(Integer uid, String startTime, String endTime);
}
