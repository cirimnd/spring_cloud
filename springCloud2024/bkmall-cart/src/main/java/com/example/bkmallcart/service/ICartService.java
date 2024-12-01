package com.example.bkmallcart.service;

import com.example.bkmallcart.domain.dto.CartDto;
import com.example.bkmallcart.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ICartService extends IService<Cart> {

    CartDto addToCart(Long userId, Long bookId, Integer quantity);

    CartDto updateCartQuantity(Long cartId, Integer newQuantity);

    boolean removeFromCart(Long cartId);

    List<CartDto> getCartList(Long userId);

}