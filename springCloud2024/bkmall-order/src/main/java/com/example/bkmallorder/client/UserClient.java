package com.example.bkmallorder.client;

import com.example.bkmallcommon.domain.dto.UserDto;
import com.example.bkmallcommon.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/dto/{id}")
    UserDto getUserById(@PathVariable("id") long id);
}
