package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE
}, allowCredentials = "true")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/manager/book/get")
    public Result getAllBooks() {
        return Result.success(managerService.getAllBooks());
    }

    @RequestMapping("/manager/book/add")
    public Result addBook(@RequestBody BookDetail book) {
        return Result.success(managerService.addBook(book));
    }

    @RequestMapping("/manager/book/update")
    public Result updateBook(@RequestBody BookDetail book) {
        return Result.success(managerService.updateBook(book));
    }

    @RequestMapping("/manager/user/get")
    public Result getAllUsers() {
        return Result.success(managerService.getAllUsers());
    }

    @RequestMapping("/manager/user/disable/{id}")
    public Result disableUser(@PathVariable("id") Integer id) {
        managerService.disableUser(id);
        return Result.success("封禁成功");
    }
}
