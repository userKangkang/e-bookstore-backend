package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.Cart;

import java.util.List;

public interface CartService {

    public List<Cart> getAllCartsByUid(int uid);

    public void removeCart(int cid);

    public void removeAllCart(int uid);

    public void updateSingleCartNumber(Cart cart);

    public void updateAllCartNumber(List<Cart> carts);
}
