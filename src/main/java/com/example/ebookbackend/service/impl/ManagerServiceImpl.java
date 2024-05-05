package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.ManagerDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Override
    public List<BookDetail> getAllBooks() {
        return managerDao.getAllBooks();
    }

    @Override
    public BookDetail addBook(BookDetail book) {
        return managerDao.addBook(book);
    }

    @Override
    public Integer updateBook(BookDetail book) {
        return managerDao.updateBook(book);
    }

    @Override
    public List<User> getAllUsers() {
        return managerDao.getAllUsers();
    }

    @Override
    public void disableUser(Integer id) {
        managerDao.disableUser(id);
    }
}
