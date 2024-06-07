package com.example.ebookbackend.service.impl;

import com.example.ebookbackend.dao.BookDao;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.BookDetail;
import com.example.ebookbackend.domain.Cart;
import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.DTO.OrderReceiverDTO;
import com.example.ebookbackend.service.BookDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Integer insertOrder(OrderReceiverDTO orderReceiverDTO) {

        int money = 0;
        for(OrderBook orderBook : orderReceiverDTO.getOrderBooks()) {
            money += orderBook.getNumber() * orderBook.getPrices();
            orderBook.setPrices(orderBook.getNumber() * orderBook.getPrices());
        }

        for(OrderBook order : orderReceiverDTO.getOrderBooks()) {
            if(order.getNumber() > bookDetailDao.getStockById(order.getBook_id())) {
                return -1;
            }
        }
        if(orderReceiverDTO.getMoney() - userDao.getBalanceById(orderReceiverDTO.getUid()) > 0) {
            return -2;
        }

        orderReceiverDTO.setMoney(money);
        return bookDetailDao.insertOrder(orderReceiverDTO);
    }

    @Override
    public Cart insertCart(Cart cart, Integer uid) {
        cart.setPrices(cart.getPrices() * cart.getNumber());
        return bookDetailDao.insertCart(cart, uid);
    }

    @Override
    public List<BookDetail> getBooksByPagination(Integer page, Integer size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return bookDetailDao.getBooksByPagination(pageable);
    }
}
