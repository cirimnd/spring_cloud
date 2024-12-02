package com.example.bkmallcommon.client;

import com.example.bkmallcommon.domain.dto.BookDto;
import com.example.bkmallcommon.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("book-service")
public interface BookClient {

    @GetMapping("/book/dto/{id}")
    BookDto getBookById(@PathVariable("id") Long id);
}
