package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;

import java.util.List;

public interface OrderService {
    public List<OrderUser> getAllOrderByUid(int uid);
}
