package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/user/orders/{uid}")
    public Result getAllOrderByUid(@PathVariable(name = "uid") int uid) {
        return Result.success(orderService.getAllOrderByUid(uid));
    }
}
