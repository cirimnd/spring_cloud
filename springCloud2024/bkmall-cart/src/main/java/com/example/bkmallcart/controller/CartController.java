package com.example.bkmallcart.controller;

import com.example.bkmallcart.domain.dto.CartDto;
import com.example.bkmallcart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    public CartDto addToCart(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam Integer quantity) {
        return cartService.addToCart(userId, bookId, quantity);
    }

    // 更新购物车商品数量
    @PutMapping("/update/{cartId}")
    public CartDto updateCartQuantity(@PathVariable Long cartId, @RequestParam Integer newQuantity) {
        return cartService.updateCartQuantity(cartId, newQuantity);
    }

    // 删除购物车商品
    @DeleteMapping("/remove/{cartId}")
    public boolean removeFromCart(@PathVariable Long cartId) {
        return cartService.removeFromCart(cartId);
    }

    // 获取购物车列表
    @GetMapping("/list/{userId}")
    public List<CartDto> getCartList(@PathVariable Long userId) {
        return cartService.getCartList(userId);
    }
}
