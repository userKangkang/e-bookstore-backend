package com.example.ebookbackend.controller;


import com.example.ebookbackend.DTO.UserSignupDTO;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.domain.UserAuth;
import com.example.ebookbackend.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result verifyUserLogin(@RequestBody UserAuth userAuth) {
        return userService.verifyByUsername(userAuth.getUsername(), userAuth.getPassword());
    }

    @RequestMapping("/user/getme")
    public Result getMe() {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        String username = session.getAttribute("username").toString();
        return Result.success(userService.getUserByUsername(username));
    }

    @RequestMapping("/user/signup")
    public Result SignupNewUser(@RequestBody UserSignupDTO user) {
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

    @RequestMapping("/user/stat/{startTime}/{endTime}")
    public Result getUserStat(@PathVariable String startTime, @PathVariable String endTime) {
        HttpSession session = SessionUtil.getSession();
        int uid = (int)session.getAttribute("uid");
        return Result.success(userService.getUserStat(uid, startTime, endTime));
    }

    @Autowired
    UserService userService;


    @RequestMapping("/user/logout")
    public Result logOut(){
        SessionUtil.removeSession();
        return Result.success("logout");
    }
}
