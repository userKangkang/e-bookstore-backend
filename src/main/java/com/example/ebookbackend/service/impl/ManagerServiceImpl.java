package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.DTO.RankBookNumberDTO;
import com.example.ebookbackend.dao.ManagerDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.OrderUser;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.service.ManagerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Autowired
    EntityManager entityManager;

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

    @Override
    public void enableUser(Integer id) {
        managerDao.enableUser(id);
    }

    @Override
    public List<BookDetail> getBooksBySearch(String search) {
        return managerDao.getBooksBySearch(search);
    }

    @Override
    public void deleteBook(Integer id) {
        managerDao.deleteBook(id);
    }

    @Override
    public List<OrderUser> getAllOrders() {
        return managerDao.getAllOrders();
    }

    @Override
    public List<OrderUser> getOrdersByTime(String startTime, String endTime){
        return managerDao.getOrdersByTime(startTime, endTime);
    }

    @Override
    public List<OrderUser> getOrderByBookName(String name) {
        return managerDao.getOrderByBookName(name);
    }

    @Override
    public List<RankBookNumberDTO> getBookRank(String startTime, String endTime) {
        Query query = entityManager.createNamedQuery("rankBookQ", RankBookNumberDTO.class);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        List<RankBookNumberDTO> res = query.getResultList();
        return res;
    }
}
