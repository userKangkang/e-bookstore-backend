package com.example.ebookbackend.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "order_table")
public class OrderBook {
    @Id
    // 指定了主键的生成策略，自增策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "prices")
    private int prices;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "oid", insertable = false, updatable = false)
    private OrderUser orderUser;

    @ManyToOne
    @JsonIgnoreProperties({"id", "stock","detail", "author"})
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookDetail book;

    @Column(name = "oid")
    private int oid;

    @Column(name = "book_id")
    private int book_id;

}
