package com.example.bkmallorder.controller;


import com.example.bkmallcommon.domain.dto.BookDto;
import com.example.bkmallcommon.domain.dto.UserDto;
import com.example.bkmallcommon.pojo.Result;
import com.example.bkmallcommon.client.BookClient;
import com.example.bkmallcommon.client.UserClient;
import com.example.bkmallorder.domain.vo.OrderVo;
import com.example.bkmallorder.entity.Order;
import com.example.bkmallorder.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final BookClient bookClient;
    private final UserClient userClient;

    /**
     * 插入或更新订单
     * @param order
     * @return
     */
    @PostMapping("/edit")
    public Result createOrUpdate(@RequestBody Order order) {
        order.setOrderDatetime(LocalDateTime.now());
        orderService.saveOrUpdate(order);
        return Result.success("更新成功");
    }

    /**
     * 确认支付
     * @param orderId
     * @return
     */
    @PutMapping("/confirm/{orderId}")
    public Result confirm(@PathVariable Long orderId) {
        Order order = orderService.getById(orderId);
        order.setState(1);
        boolean flg = orderService.updateById(order);
        if(flg) return Result.success("确认成功");
        else return Result.error("确认失败");
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @DeleteMapping("/delete/{orderId}")
    public Result deleteById(@PathVariable Long orderId) {
        boolean flg = orderService.removeById(orderId);
        if(flg) return Result.success("删除成功");
        else return Result.error("删除失败");
    }

    //根据id查询
    @GetMapping("/{orderId}")
    public Result getById(@PathVariable Long orderId) {
        return Result.success(orderService.getById(orderId));
    }

    //查询全部
    @GetMapping
    public Result list() {return Result.success(orderService.list());}

    //根据用户id查询该用户所有订单及其中书本信息
    @GetMapping("/user/{userId}")
    public Result getByUserId(@PathVariable Long userId) {
        List<Order> orderList = orderService.getByUserId(userId);
        List<OrderVo> orderVoList = orderList.stream().map(this::ToOrderVo).toList();
        return Result.success(orderVoList);
    }

    //根据书本id查询关于该书本所有订单
    @GetMapping("/book/{bookId}")
    public Result getByBookId(@PathVariable Long bookId) {
        List<Order> orderList = orderService.getByBookId(bookId);
        List<OrderVo> orderVoList = orderList.stream().map(this::ToOrderVo).toList();
        return Result.success(orderVoList);
    }

    private OrderVo ToOrderVo(Order order){
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(order.getOrderId());
        orderVo.setOrderState(order.getState());
        orderVo.setOrderDatetime(order.getOrderDatetime());
        orderVo.setAddress(order.getAddress());
        orderVo.setBookPrice(order.getBookPrice());

        BookDto bookDto =bookClient.getBookById(order.getBookId());

        orderVo.setBookAuthor(bookDto.getAuthor());
        orderVo.setBookTitle(bookDto.getTitle());

        UserDto userDto = userClient.getUserById(order.getUserId());
        orderVo.setUsername(userDto.getUserName());

        return orderVo;
    }
}
