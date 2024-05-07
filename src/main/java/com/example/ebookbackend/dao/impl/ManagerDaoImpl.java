package com.example.ebookbackend.dao.impl;

import com.example.ebookbackend.dao.ManagerDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.repo.BookDetailRepository;
import com.example.ebookbackend.repo.OrderUserRepository;
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

    @Autowired
    OrderUserRepository orderUserRepository;

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
        return bookDetailRepository.updateBookDetailPart(book.getName(), book.getAuthor(),
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

    @Override
    public void enableUser(Integer id) {
        userRepository.enableUser(id);
    }

    @Override
    public List<BookDetail> getBooksBySearch(String search) {
        return bookDetailRepository.getBooksBySearch(search);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDetailRepository.deleteBookDetailById(id);
    }

    @Override
    public List<OrderUser> getAllOrders(){
        return orderUserRepository.findAll();
    }


    @Override
    public List<OrderUser> getOrdersByTime(String startTime, String endTime)
    {
        return orderUserRepository.getOrderUserByTime(startTime, endTime);
    }

    @Override
    public List<OrderUser> getOrderByBookName(String name) {
        return orderUserRepository.getOrderUserByBookName(name);
    }
}
