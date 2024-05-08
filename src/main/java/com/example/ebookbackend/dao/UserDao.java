package com.example.ebookbackend.dao;

import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.domain.User;

import java.util.List;

public interface UserDao {
    public String getPasswordByUsername(String username);

    public User insertNewUser(User user);

    public Integer updateProfile(User user);

    public User getUserByUsername(String username);

    public Integer getBalanceById(Integer id);

    List<StatisticsUserDTOInterface> getUserStatInfo(Integer uid, String startTime, String endTime);
}
