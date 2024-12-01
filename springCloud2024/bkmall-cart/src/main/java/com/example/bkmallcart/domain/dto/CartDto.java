package com.example.bkmallcart.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDto {

    private Long cartId;

    private Long userId;

    private Long bookId;

    private String bookTitle;

    private String bookAuthor;

    private Integer quantity;

    private BigDecimal totalPrice;

}
