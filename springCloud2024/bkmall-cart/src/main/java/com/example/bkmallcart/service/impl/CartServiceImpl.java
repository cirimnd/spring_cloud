package com.example.bkmallcart.service.impl;

import com.example.bkmallorder.client.BookClient;
import com.example.bkmallcart.domain.dto.CartDto;
import com.example.bkmallcart.entity.Cart;
import com.example.bkmallcart.mapper.CartMapper;
import com.example.bkmallcart.service.ICartService;
import com.example.bkmallcommon.domain.dto.BookDto;
import com.example.bkmallcommon.pojo.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Resource
    private final BookClient bookClient;

    @Override
    public CartDto addToCart(Long userId, Long bookId, Integer quantity) {
        // 先查询购物车中是否已存在该商品
        Cart existingCart = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .eq(Cart::getBookId, bookId)
                .one();

        if (existingCart!= null) {
            // 如果存在，更新数量和总价
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            existingCart.setTotalPrice(existingCart.getTotalPrice().add(getBookPrice(bookId).multiply(BigDecimal.valueOf(quantity))));
            updateById(existingCart);
        } else {
            // 如果不存在，创建新的购物车记录
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setBookId(bookId);
            newCart.setQuantity(quantity);
            newCart.setTotalPrice(getBookPrice(bookId).multiply(BigDecimal.valueOf(quantity)));
            newCart.setAddTime(LocalDateTime.now());
            save(newCart);
            existingCart = newCart;
        }

        return entityToDto(existingCart);
    }

    @Override
    public CartDto updateCartQuantity(Long cartId, Integer newQuantity) {
        Cart cart = getById(cartId);
        if (cart!= null) {
            cart.setQuantity(newQuantity);
            cart.setTotalPrice(getBookPrice(cart.getBookId()).multiply(BigDecimal.valueOf(newQuantity)));
            updateById(cart);
            return entityToDto(cart);
        }
        return null;
    }

    @Override
    public boolean removeFromCart(Long cartId) {
        return removeById(cartId);
    }

    @Override
    public List<CartDto> getCartList(Long userId) {
        List<Cart> cartList = lambdaQuery()
                .eq(Cart::getUserId, userId)
                .list();

        return cartList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    private CartDto entityToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setUserId(cart.getUserId());
        cartDto.setBookId(cart.getBookId());

        BookDto bookDto = bookClient.getBookById(cart.getBookId());
        cartDto.setBookTitle(bookDto.getTitle());
        cartDto.setBookAuthor(bookDto.getAuthor());

        cartDto.setQuantity(cart.getQuantity());
        cartDto.setTotalPrice(cart.getTotalPrice());

        return cartDto;
    }

    private BigDecimal getBookPrice(Long bookId) {
        BookDto bookDto = bookClient.getBookById(bookId);

        Result bookResult;
        if (bookDto!= null && bookDto.getPrice()!= null) {
            bookResult = Result.success(bookDto);
        } else {
            bookResult = Result.error("获取书籍信息失败");
        }

        if (bookResult.isSuccess()) {
            Object data = bookResult.getData();
            if (data instanceof BookDto) {
                BookDto bookDto1 = (BookDto) data;
                return bookDto1.getPrice();
            } else {
                // 如果获取到的数据不是BookDto类型，可能是出现了错误或者数据不匹配的情况，返回默认值
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }
}