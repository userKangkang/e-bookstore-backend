package com.example.ebookbackend.repo;

import com.example.ebookbackend.domain.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BookDetailRepository extends JpaRepository<BookDetail, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update BookDetail b set b.name = :name, b.author = :author, b.detail = :detail, b.path = :path, b.price = :price, b.stock = :stock where b.id = :id")
    int updateBookDetailPart(@Param("name") String name, @Param("author") String author, @Param("detail") String detail,
                             @Param("path") String path, @Param("price") Integer price,
                             @Param("stock") Integer stock, @Param("id") Integer id);

    @Query(nativeQuery = true, value = "select * from book_table where name like concat('%', :search, '%') ")
    List<BookDetail> getBooksBySearch(String search);

    @Modifying
    @Transactional
    void deleteBookDetailById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update BookDetail b set b.stock = b.stock - :number where b.id = :id")
    void reduceBookStock(Integer number, Integer id);

    @Query(value = "select b.stock from BookDetail b where b.id = :id")
    Integer findStockById(Integer id);
}
