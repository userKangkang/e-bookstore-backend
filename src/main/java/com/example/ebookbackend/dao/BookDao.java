package com.example.ebookbackend.dao;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.DTO.OrderReceiverDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookDao {


    public List<BookDetail> getBooksByPagination(Pageable pageable);

    public BookDetail getBookById(Integer id);

    Integer insertOrder(OrderReceiverDTO orderReceiverDTO);

    Cart insertCart(Cart cart, Integer uid);

    Integer getStockById(Integer id);
}
