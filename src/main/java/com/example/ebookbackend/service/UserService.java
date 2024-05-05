package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.domain.User;
import org.springframework.web.multipart.MultipartFile;


public interface UserService {
    public Result verifyByUsername(String username, String password);

    public Result insertNewUser(User user);

    public Result uploadAvatar(MultipartFile avatar) throws Exception ;

    public Result updateProfile(User user);

    public User getUserByUsername(String username);
}
