package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<OrderUser> getAllOrderByUid(int uid) {
        return orderDao.getAllOrderByUserId(uid);
    }

    @Override
    public List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime) {
        return orderDao.getUserOrdersByTime(uid, startTime, endTime);
    }

    @Override
    public List<OrderUser> getUserOrdersByBookName(String name, Integer uid) {
        return orderDao.getUserOrdersByBookName(name, uid);
    }
}
