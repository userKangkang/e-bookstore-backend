package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderUserRepository extends JpaRepository<OrderUser, Integer> {
}
