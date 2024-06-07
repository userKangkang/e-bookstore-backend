package com.example.ebookbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private String username;
    @Transient
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "balance")
    private int balance;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "hobby")
    private String hobby;
    @Column(name = "signature")
    private String signature;

    @Transient
    private int state;
    @Column(name = "identity")
    private int identity;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderUser> orderUser;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserAuth userAuth;
}
