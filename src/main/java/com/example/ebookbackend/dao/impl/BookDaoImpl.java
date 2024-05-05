package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.receiver.OrderReceiver;
import com.example.ebookbackend.repo.BookDetailRepository;
import com.example.ebookbackend.repo.CartRepository;
import com.example.ebookbackend.repo.OrderBookRepository;
import com.example.ebookbackend.repo.OrderUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    BookDetailRepository bookDetailRepository;

    @Autowired
    OrderBookRepository orderBookRepository;

    @Autowired
    OrderUserRepository orderUserRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    public BookDetail getBookById(Integer id) {
        return bookDetailRepository.getById(id);
    }

    @Override
    public Integer insertOrder(OrderReceiver orderReceiver) {
        OrderUser orderUser = new OrderUser();
        orderUser.setUid(orderReceiver.getUid());
        orderUser.setTime(orderReceiver.getTime());
        orderUser.setAddress(orderReceiver.getAddress());
        orderUser.setMoney(orderReceiver.getMoney());
        int oid = orderUserRepository.save(orderUser).getOrderId();
        orderReceiver.setOrderBooksOid(oid);
        orderBookRepository.saveAll(orderReceiver.getOrderBooks());
        return oid;
    }

    @Override
    public Cart insertCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
