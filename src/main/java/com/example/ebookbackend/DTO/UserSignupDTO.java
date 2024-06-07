package com.example.ebookbackend.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {

    private int id;

    private String username;

    private String password;

    private String email;

    private int balance;

    private String avatar;

    private String hobby;

    private String signature;
}
