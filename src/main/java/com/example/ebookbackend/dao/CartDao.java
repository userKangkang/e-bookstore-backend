package com.example.ebookbackend.dao;

import com.example.ebookbackend.DTO.OrderReceiverDTO;
import com.example.ebookbackend.domain.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> getAllCartsByUid(int uid);

    void removeCart(int cid);

    void removeAllCart(int uid);

    void updateSingleCartNumber(Cart cart);

    void updateAllCartNumber(List<Cart> carts);

    int buyBooksByCart(OrderReceiverDTO orderReceiverDTO);
}
