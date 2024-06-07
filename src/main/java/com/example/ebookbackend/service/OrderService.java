package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface OrderService {
    public List<OrderUser> getAllOrderByUid(int uid, Pageable pageable);

    public Integer getAllOrderNumberByUid(int uid);

    public List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime, Integer page, Integer size);

    public Integer getOrderNumberByTime(Integer uid, String startTime, String endTime);

    public List<OrderUser> getUserOrdersByBookName(String name, Integer uid, Integer page, Integer size);

    public Integer getOrderNumberByBookName(Integer uid, String bookName);
}
