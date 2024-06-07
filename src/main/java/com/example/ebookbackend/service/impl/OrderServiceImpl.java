package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public List<OrderUser> getAllOrderByUid(int uid, Pageable pageable) {
        return orderDao.getAllOrderByUserId(uid, pageable);
    }

    @Override
    public List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime, Integer page, Integer size) {
        int start = page * size;
        int end = Math.min(orderDao.getUserOrdersByTime(uid, startTime, endTime).size(), (page + 1) * size);
        return orderDao.getUserOrdersByTime(uid, startTime, endTime).subList(start, end);
    }

    @Override
    public Integer getOrderNumberByTime(Integer uid, String startTime, String endTime) {
        return orderDao.getUserOrdersByTime(uid, startTime, endTime).size();
    }

    @Override
    public List<OrderUser> getUserOrdersByBookName(String name, Integer uid, Integer page, Integer size) {
        int start = page * size;
        int end = Math.min(orderDao.getUserOrdersByBookName(name, uid).size(), (page + 1) * size);
        return orderDao.getUserOrdersByBookName(name, uid).subList(start, end);
    }

    @Override
    public Integer getAllOrderNumberByUid(int uid)
    {
        return orderDao.getAllOrderNumberByUid(uid);
    }

    @Override
    public Integer getOrderNumberByBookName(Integer uid, String bookName)
    {
        return orderDao.getUserOrdersByBookName(bookName, uid).size();
    }
}
