package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderUserRepository extends JpaRepository<OrderUser, Integer> {

    @Query(nativeQuery = true, value = "select * from order_user_table ou where ou.time between :startTime and :endTime")
    List<OrderUser> getOrderUserByTime(String startTime, String endTime);

    @Query(nativeQuery = true, value = "select * from order_user_table ou where ou.uid = :uid and (ou.time between :startTime and :endTime)")
    List<OrderUser> getUserOrdersByTime(Integer uid, String startTime, String endTime);

    @Query(value = "select ou from OrderUser ou join OrderBook ob on ou.orderId = ob.oid join BookDetail b on ob.book_id = b.id where b.name like concat('%', :name, '%') ")
    List<OrderUser> getOrderUserByBookName(String name);

    @Query(value = "select ou from OrderUser ou join OrderBook ob on ou.orderId = ob.oid join BookDetail b on ob.book_id = b.id where b.name like concat('%', :name, '%') and ou.uid = :uid")
    List<OrderUser> getUserOrdersByBookName(String name, Integer uid);
}
