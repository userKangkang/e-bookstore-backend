package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.DTO.OrderReceiverDTO;
import com.example.ebookbackend.dao.CartDao;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookDetailRepository bookDetailRepository;

    @Autowired
    OrderUserRepository orderUserRepository;

    @Autowired
    OrderBookRepository orderBookRepository;

    @Override
    public List<Cart> getAllCartsByUid(int uid) {
        return userRepository.getById(uid).getCarts();
    }

    @Override
    public void removeCart(int cid) {
        cartRepository.deleteById(cid);
    }

    @Override
    public void removeAllCart(int uid) {
        cartRepository.deleteAllByUid(uid);
    }

    @Override
    public void updateSingleCartNumber(Cart cart) {
        cartRepository.updateNumberById(cart.getNumber(), cart.getId());
    }

    @Override
    public void updateAllCartNumber(List<Cart> carts) {
        for(Cart c : carts) {
            cartRepository.updateNumberById(c.getNumber(), c.getId());
        }
    }

    @Override
    public int buyBooksByCart(OrderReceiverDTO orderReceiverDTO) {


        for(OrderBook order : orderReceiverDTO.getOrderBooks()) {
            if(order.getNumber() > bookDetailRepository.findStockById(order.getBook_id())) {
                return -1;
            }
        }
        if(orderReceiverDTO.getMoney() - userRepository.getBalanceById(orderReceiverDTO.getUid()) > 0) {
            return -2;
        }


        for(OrderBook order : orderReceiverDTO.getOrderBooks()) {
            bookDetailRepository.reduceBookStock(order.getNumber(), order.getBook_id());
        }
        OrderUser orderUser = new OrderUser();
        orderUser.setUid(orderReceiverDTO.getUid());
        orderUser.setTime(orderReceiverDTO.getTime());
        orderUser.setAddress(orderReceiverDTO.getAddress());
        orderUser.setMoney(orderReceiverDTO.getMoney());
        userRepository.reduceBalance(orderUser.getMoney(), orderUser.getUid());
        int oid = orderUserRepository.save(orderUser).getOrderId();
        orderReceiverDTO.setOrderBooksOid(oid);
        orderBookRepository.saveAll(orderReceiverDTO.getOrderBooks());
        return oid;
    }

}
