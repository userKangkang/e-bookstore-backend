package com.example.ebookbackend.dao;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;

import java.util.List;

public interface OrderDao {

    List<OrderUser> getAllOrderByUserId(int uid);


}
