package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Modifying
    @Transactional
    void deleteAllByUid(int uid);

    @Modifying
    @Transactional
    @Query(value = "update Cart c set c.prices = c.prices / c.number * :number, c.number = :number where c.id = :cid")
    void updateNumberById(int number ,int cid);

    Cart findCartByBook_idAndUid(Integer book_id, Integer uid);
}
