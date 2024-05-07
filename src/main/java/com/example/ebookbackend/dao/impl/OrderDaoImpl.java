package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.repo.OrderBookRepository;
import com.example.ebookbackend.repo.OrderUserRepository;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    OrderBookRepository orderBookRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderUserRepository orderUserRepository;

    @Override
    public List<OrderUser> getAllOrderByUserId(int uid) {
        User user = userRepository.getById(uid);
        return user.getOrderUser();
    }

    @Override
    public List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime)
    {
        return orderUserRepository.getUserOrdersByTime(uid, startTime, endTime);
    }

    @Override
    public List<OrderUser> getUserOrdersByBookName(String name, Integer uid) {
        return orderUserRepository.getUserOrdersByBookName(name, uid);
    }
}
