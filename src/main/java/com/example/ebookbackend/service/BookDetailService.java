package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.receiver.OrderReceiver;


public interface BookDetailService {
//    void addBook(BookDetail bd);
//
//    void deleteBook(BookDetail bd);

    BookDetail getBook(Integer id);

    Integer insertOrder(OrderReceiver orderReceiver);

    Cart insertCart(Cart cart, Integer uid);

}
