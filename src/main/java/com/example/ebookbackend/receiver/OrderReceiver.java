package com.example.ebookbackend.receiver;


import com.example.ebookbackend.domain.OrderBook;
import com.example.ebookbackend.domain.OrderUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReceiver {
    private int uid;

    private String address;

    private int money;

    private Date time;

    private List<OrderBook> orderBooks;

    public void setOrderBooksOid(int oid) {
        for(int i = 0; i < orderBooks.size(); i++) {
            orderBooks.get(i).setOid(oid);
        }
    }
}
