package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.repo.OrderUserRepository;
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
    public Result disableUser(@PathVariable(name = "id") Integer id) {
        managerService.disableUser(id);
        return Result.success("封禁成功");
    }

    @RequestMapping("/manager/user/enable/{id}")
    public Result enableUser(@PathVariable(name = "id") Integer id) {
        managerService.enableUser(id);
        return Result.success("解禁成功");
    }

    @RequestMapping("/manager/book/search/{search}")
    public Result getBooksBySearch(@PathVariable(name = "search") String search) {
        return Result.success(managerService.getBooksBySearch(search));
    }

    @RequestMapping("/manager/book/delete/{id}")
    public Result deleteBookById(@PathVariable(name = "id") Integer id) {
        managerService.deleteBook(id);
        return Result.success("删除成功");
    }

    @RequestMapping("/manager/order/all")
    public Result getAllOrders() {
        return Result.success(managerService.getAllOrders());
    }

    @RequestMapping("/manager/order/time/{startTime}/{endTime}")
    public Result getOrdersByTime(@PathVariable(name = "startTime") String startTime,
                                  @PathVariable(name = "endTime") String endTime) {
        return Result.success(managerService.getOrdersByTime(startTime, endTime));
    }

    @RequestMapping("/manager/order/name/{bookname}")
    public Result getOrdersByBookName(@PathVariable String bookname)
    {
        return Result.success(managerService.getOrderByBookName(bookname));
    }

    @RequestMapping("/manager/rank/book/{startTime}/{endTime}")
    public Result getBookRank(@PathVariable(name = "startTime") String startTime,
                              @PathVariable(name = "endTime") String endTime) {
        return Result.success(managerService.getBookRank(startTime, endTime));
    }

    @RequestMapping("/manager/rank/user/{startTime}/{endTime}")
    public Result getUserRank(@PathVariable(name = "startTime") String startTime,
                              @PathVariable(name = "endTime") String endTime) {
        return Result.success(managerService.getUserRank(startTime, endTime));
    }
}
