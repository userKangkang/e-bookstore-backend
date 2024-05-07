package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.receiver.OrderReceiver;
import com.example.ebookbackend.service.BookDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class BookDetailServiceImpl implements BookDetailService {

    @Autowired
    private final BookDao bookDetailDao;

    @Autowired
    private final UserDao userDao;

//    @Override
//    public void addBook(BookDetail bd){
//        bookDetailRepo.save(bd);
//    }
//
//    @Override
//    public void deleteBook(BookDetail bd){
//        bookDetailRepo.deleteById(bd.getId());
//    }

    @Override
    public BookDetail getBook(Integer id){
        return bookDetailDao.getBookById(id);
    }

    @Override
    public Integer insertOrder(OrderReceiver orderReceiver) {
        for(OrderBook order : orderReceiver.getOrderBooks()) {
            if(order.getNumber() > bookDetailDao.getStockById(order.getBook_id())) {
                return -1;
            }
        }
        if(orderReceiver.getMoney() > userDao.getBalanceById(orderReceiver.getUid())) {
            return -2;
        }
        return bookDetailDao.insertOrder(orderReceiver);
    }

    @Override
    public Cart insertCart(Cart cart) {
        return bookDetailDao.insertCart(cart);
    }
}
