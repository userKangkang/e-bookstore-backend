package com.example.ebookbackend.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RankBookNumberDTO {
    int rank;
    int book_id;
    int totalNumber;
    String name;
    String avatar;
    String author;
}
