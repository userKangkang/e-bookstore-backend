package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.OrderDao;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.repo.OrderBookRepository;
import com.example.ebookbackend.repo.OrderUserRepository;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public List<OrderUser> getAllOrderByUserId(int uid, Pageable pageable) {
        User user = userRepository.getById(uid);
        int number = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int max = Math.min(user.getOrderUser().size(), (number + 1) * size);
        return user.getOrderUser().subList(number * size, max);
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

    @Override
    public Integer getAllOrderNumberByUid(int uid) {
        return userRepository.getById(uid).getOrderUser().size();
    }
}
