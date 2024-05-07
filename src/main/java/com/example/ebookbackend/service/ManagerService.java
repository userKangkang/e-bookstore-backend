package com.example.ebookbackend.service;

import com.example.ebookbackend.DTO.RankBookNumberDTO;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.domain.User;

import java.util.List;

public interface ManagerService {

    List<BookDetail> getAllBooks();

    BookDetail addBook(BookDetail book);

    Integer updateBook(BookDetail book);

    List<User> getAllUsers();

    void disableUser(Integer id);

    void enableUser(Integer id);

    List<BookDetail> getBooksBySearch(String search);

    void deleteBook(Integer id);

    List<OrderUser> getAllOrders();

    List<OrderUser> getOrdersByTime(String startTime, String endTime);

    List<OrderUser> getOrderByBookName(String name);

    List<RankBookNumberDTO> getBookRank(String startTime, String endTime);
}
