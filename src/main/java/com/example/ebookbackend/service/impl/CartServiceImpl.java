package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.DTO.OrderReceiverDTO;
import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.dao.CartDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;


    @Override
    public List<Cart> getAllCartsByUid(int uid) {
        return cartDao.getAllCartsByUid(uid);
    }

    @Override
    public void removeCart(int cid) {
        cartDao.removeCart(cid);
    }

    @Override
    public void removeAllCart(int uid) {
        cartDao.removeAllCart(uid);
    }

    @Override
    public void updateSingleCartNumber(Cart cart) {
        cartDao.updateSingleCartNumber(cart);
    }

    @Override
    public void updateAllCartNumber(List<Cart> carts) {
        cartDao.updateAllCartNumber(carts);
    }

    @Override
    public int buyBooksByCart(OrderReceiverDTO orderReceiverDTO) {
        return cartDao.buyBooksByCart(orderReceiverDTO);
    }
}
