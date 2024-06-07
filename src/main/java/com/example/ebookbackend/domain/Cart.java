package com.example.ebookbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "cart_table")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "prices")
    private int prices;

    @Column(name = "uid")
    private int uid;

    @Column(name = "book_id")
    private int book_id;

    @Column(name = "time")
    private Date time;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JsonIgnoreProperties({"id", "stock","detail", "author"})
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private BookDetail book;
}
