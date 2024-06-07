package com.example.ebookbackend.controller;

import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.service.OrderService;
import com.example.ebookbackend.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {
        RequestMethod.GET, RequestMethod.POST,
        RequestMethod.PUT, RequestMethod.DELETE
}, allowCredentials = "true")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/user/orders/{page}/{size}")
    public Result getAllOrderByUid(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        HttpSession session = SessionUtil.getSession();
        Pageable pageable = PageRequest.of(page, size);
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getAllOrderByUid(uid, pageable));
    }

    @RequestMapping("/user/orders/number")
    public Result getAllOrderNumberByUid()
    {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getAllOrderNumberByUid(uid));
    }

    @RequestMapping("/user/orders/time/{startTime}/{endTime}/{page}/{size}")
    public Result getUserOrdersByTime(
                                      @PathVariable(name = "startTime") String startTime,
                                      @PathVariable(name = "endTime") String endTime,
                                      @PathVariable(name = "page") Integer page,
                                      @PathVariable(name = "size") Integer size)
    {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getUserOrdersByTime(uid, startTime, endTime, page, size));
    }

    @RequestMapping("/user/orders/number/{startTime}/{endTime}")
    public Result getOrderNumberByTime(@PathVariable(name = "startTime") String startTime,
                                       @PathVariable(name = "endTime") String endTime) {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getOrderNumberByTime(uid, startTime, endTime));
    }

    @RequestMapping("user/orders/name/{name}/{page}/{size}")
    public Result getUserOrderByBookName(@PathVariable(name = "name") String name,
                                         @PathVariable(name = "page") Integer page,
                                         @PathVariable(name = "size") Integer size) {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getUserOrdersByBookName(name, uid, page, size));
    }

    @RequestMapping("user/orders/number/{name}")
    public Result getOrderNumberByBookName(@PathVariable("name") String bookName) {
        HttpSession session = SessionUtil.getSession();
        assert session != null;
        int uid = (int)session.getAttribute("uid");
        return Result.success(orderService.getOrderNumberByBookName(uid, bookName));
    }
}
