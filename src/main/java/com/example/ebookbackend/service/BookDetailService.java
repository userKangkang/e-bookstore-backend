package com.example.ebookbackend.service;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.DTO.OrderReceiverDTO;

import java.util.List;


public interface BookDetailService {
//    void addBook(BookDetail bd);
//
//    void deleteBook(BookDetail bd);

    BookDetail getBook(Integer id);

    Integer insertOrder(OrderReceiverDTO orderReceiverDTO);

    Cart insertCart(Cart cart, Integer uid);

    List<BookDetail> getBooksByPagination(Integer page, Integer size);

}
