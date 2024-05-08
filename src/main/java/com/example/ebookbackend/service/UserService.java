package com.example.ebookbackend.service;

import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
    public Result verifyByUsername(String username, String password);

    public Result insertNewUser(User user);

    public Result uploadAvatar(MultipartFile avatar) throws Exception ;

    public Result updateProfile(User user);

    public User getUserByUsername(String username);

    List<StatisticsUserDTOInterface> getUserStat(Integer uid, String startTime, String endTime);
}
