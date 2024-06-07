package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.DTO.OrderReceiverDTO;
import com.example.ebookbackend.service.BookDetailService;
import com.example.ebookbackend.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE
}, allowCredentials = "true")
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
    public Result insertOrder(@RequestBody OrderReceiverDTO orderReceiverDTO) {
        Integer res = bdService.insertOrder(orderReceiverDTO);
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
    public Result insertCart( @RequestBody Cart cart) {
        HttpSession session = SessionUtil.getSession();
        int uid = (int)session.getAttribute("uid");
        Cart res = bdService.insertCart(cart, uid);
        if(res == null) {
            return Result.failure("购物车内已存在此书", false);
        } else {
            return Result.success(res);
        }
    }

    @RequestMapping("/book/all/pagination/{page}/{size}")
    public Result getAllBooksByPagination(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return Result.success(bdService.getBooksByPagination(page, size));
    }

    @RequestMapping("book/all/sum")
    public Result getAllBookSum() {
        return Result.success(9);
    }
}
