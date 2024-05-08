package com.example.ebookbackend.controller;


import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.ebookbackend.service.UserService;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE
}, allowCredentials = "true")
@RestController
public class UserController {


    @RequestMapping("/user/login")
    public Result verifyUserLogin(@RequestBody User user) {
        return userService.verifyByUsername(user.getUsername(), user.getPassword());
    }

    @RequestMapping("/user/getme")
    public Result getMe(@RequestBody User user) {
        return Result.success(userService.getUserByUsername(user.getUsername()));
    }

    @RequestMapping("/user/signup")
    public Result SignupNewUser(@RequestBody User user) {
        return userService.insertNewUser(user);
    }

    @PostMapping("/upload/avatar")
    public Result uploadAvatar(MultipartFile avatar) throws Exception {
        System.out.println(avatar);
        return userService.uploadAvatar(avatar);
    }

    @RequestMapping("/user/profile")
    public Result updateProfile(@RequestBody User user) {
        return userService.updateProfile(user);
    }

    @RequestMapping("/user/stat/{uid}/{startTime}/{endTime}")
    public Result getUserStat(@PathVariable Integer uid, @PathVariable String startTime, @PathVariable String endTime) {
        return Result.success(userService.getUserStat(uid, startTime, endTime));
    }

    @Autowired
    UserService userService;


}
