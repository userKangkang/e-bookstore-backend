package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BookDetailRepository extends JpaRepository<BookDetail, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update BookDetail b set b.detail = :detail, b.path = :path, b.price = :price, b.stock = :stock where b.id = :id")
    int updateBookDetailPart(@Param("detail") String detail,
                             @Param("path") String path, @Param("price") Integer price,
                             @Param("stock") Integer stock, @Param("id") Integer id);

}
