package com.example.ebookbackend.dao;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDao {

    List<OrderUser> getAllOrderByUserId(int uid, Pageable pageable);

    Integer getAllOrderNumberByUid(int uid);

    List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime);

    List<OrderUser> getUserOrdersByBookName(String name, Integer uid);
}
