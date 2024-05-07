package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.CartDao;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.repo.CartRepository;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

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

}
