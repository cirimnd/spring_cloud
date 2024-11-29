package com.example.bkmallcommon.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private String title;
    private BigDecimal price;
    private String author;
}
