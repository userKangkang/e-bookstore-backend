package com.example.ebookbackend.dao;

import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.User;

import java.util.List;

public interface ManagerDao {
    List<BookDetail> getAllBooks();

    BookDetail addBook(BookDetail book);

    Integer updateBook(BookDetail book);

    List<User> getAllUsers();

    void disableUser(Integer id);
}
