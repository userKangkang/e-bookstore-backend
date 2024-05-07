package com.example.ebookbackend.repo;

import com.example.ebookbackend.DTO.RankBookNumberDTO;
import com.example.ebookbackend.domain.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {
    List<OrderBook> getOrderBooksByOid(int oid);



}
