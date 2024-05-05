package com.example.ebookbackend.dao;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.receiver.OrderReceiver;


public interface BookDao {


    public BookDetail getBookById(Integer id);

    Integer insertOrder(OrderReceiver orderReceiver);

    Cart insertCart(Cart cart);
}
