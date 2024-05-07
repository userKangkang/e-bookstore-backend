package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/user/orders/{uid}")
    public Result getAllOrderByUid(@PathVariable(name = "uid") int uid) {
        return Result.success(orderService.getAllOrderByUid(uid));
    }

    @RequestMapping("/user/orders/time/{uid}/{startTime}/{endTime}")
    public Result getUserOrdersByTime(@PathVariable(name = "uid") Integer uid,
                                      @PathVariable(name = "startTime") String startTime,
                                      @PathVariable(name = "endTime") String endTime)
    {
        return Result.success(orderService.getUserOrdersByTime(uid, startTime, endTime));
    }

    @RequestMapping("user/orders/name/{uid}/{name}")
    public Result getUserOrderByBookName(@PathVariable(name = "uid") Integer uid,
                                         @PathVariable(name = "name") String name) {
        return Result.success(orderService.getUserOrdersByBookName(name, uid));
    }
}
