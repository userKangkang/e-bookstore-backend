package com.example.ebookbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "order_user_table", schema = "bookstore", catalog = "")
public class OrderUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private int orderId;
    @Basic
    @Column(name = "uid")
    private int uid;

    @Column(name = "address")
    private String address;

    @Column(name = "money")
    private int money;

    @Column(name = "time")
    private Date time;

    @JsonIgnoreProperties({"id", "password", "email", "balance", "avatar", "hobby", "signature", "state", "identity"})
    @ManyToOne
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;

    @OneToMany
            (mappedBy = "orderUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderBook> orderBooks;


}
