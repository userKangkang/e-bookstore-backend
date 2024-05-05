package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.CartDao;
import com.example.ebookbackend.domain.Cart;
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
}
