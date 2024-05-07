package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.receiver.OrderReceiver;
import com.example.ebookbackend.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
@RestController
public class BookDetailController {

    @Autowired
    private BookDetailService bdService;

    @GetMapping("/getbook/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        return Result.success(bdService.getBook(id));
    }

    @RequestMapping("/order/add")
    public Result insertOrder(@RequestBody OrderReceiver orderReceiver) {
        Integer res = bdService.insertOrder(orderReceiver);
        if(res == -1) {
            return Result.failure("数量不足",false);
        } else if(res == -2) {
            return Result.failure("余额不足", false);
        }
        else {
            return Result.success(res);
        }
    }

    @RequestMapping("/cart/add")
    public Result insertCart(@RequestBody Cart cart) {
        return Result.success(bdService.insertCart(cart));
    }
}
