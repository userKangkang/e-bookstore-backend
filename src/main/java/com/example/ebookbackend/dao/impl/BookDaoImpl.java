package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.receiver.OrderReceiver;
import com.example.ebookbackend.repo.*;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public BookDetail getBookById(Integer id) {
        return bookDetailRepository.getById(id);
    }

    @Override
    public Integer insertOrder(OrderReceiver orderReceiver) {
        for(OrderBook order : orderReceiver.getOrderBooks()) {
            bookDetailRepository.reduceBookStock(order.getNumber(), order.getBook_id());
        }
        OrderUser orderUser = new OrderUser();
        orderUser.setUid(orderReceiver.getUid());
        orderUser.setTime(orderReceiver.getTime());
        orderUser.setAddress(orderReceiver.getAddress());
        orderUser.setMoney(orderReceiver.getMoney());
        userRepository.reduceBalance(orderUser.getMoney(), orderUser.getUid());
        int oid = orderUserRepository.save(orderUser).getOrderId();
        orderReceiver.setOrderBooksOid(oid);
        orderBookRepository.saveAll(orderReceiver.getOrderBooks());
        return oid;
    }

    @Override
    public Cart insertCart(Cart cart, Integer uid) {
        if(cartRepository.findCartByBook_idAndUid(cart.getBook_id(), uid) == null) {
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public Integer getStockById(Integer id) {
        return bookDetailRepository.findStockById(id);
    }
}
