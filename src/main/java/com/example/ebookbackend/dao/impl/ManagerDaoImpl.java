package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.ManagerDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.repo.BookDetailRepository;
import com.example.ebookbackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    BookDetailRepository bookDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<BookDetail> getAllBooks() {
        return bookDetailRepository.findAll();
    }

    @Override
    public BookDetail addBook(BookDetail book) {
        return bookDetailRepository.save(book);
    }

    @Override
    public Integer updateBook(BookDetail book) {
        return bookDetailRepository.updateBookDetailPart(
                book.getDetail(), book.getPath(), book.getPrice(), book.getStock(), book.getId()
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void disableUser(Integer id) {
        userRepository.disableUser(id);
    }
}
