package com.example.bkmallorder.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderVo {
    private String username;
    private Long orderId;
    private Integer orderState;
    private LocalDateTime orderDatetime;
    private String address;
    private Double bookPrice;
    private String bookTitle;
    private String bookAuthor;
}
