package com.example.bkmallcart.controller;

import com.example.bkmallcart.domain.dto.CartDto;
import com.example.bkmallcart.service.ICartService;
import com.example.bkmallcommon.pojo.Result;
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
    public Result addToCart(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam Integer quantity) {
        return Result.success(cartService.addToCart(userId, bookId, quantity));
    }

    // 更新购物车商品数量
    @PutMapping("/update/{cartId}")
    public Result updateCartQuantity(@PathVariable Long cartId, @RequestParam Integer newQuantity) {
        return Result.success(cartService.updateCartQuantity(cartId, newQuantity)) ;
    }

    // 删除购物车商品
    @DeleteMapping("/remove/{cartId}")
    public Result removeFromCart(@PathVariable Long cartId) {
        if(cartService.removeFromCart(cartId)) {
            return Result.success(cartId);
        }
        else return Result.error("删除失败！");
    }

    // 获取购物车列表
    @GetMapping("/list/{userId}")
    public Result getCartList(@PathVariable Long userId) {
        return Result.success(cartService.getCartList(userId));
    }
}
