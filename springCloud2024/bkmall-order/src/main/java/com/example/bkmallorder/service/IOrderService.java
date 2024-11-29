package com.example.bkmallorder.service;

import com.example.bkmallorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
public interface IOrderService extends IService<Order> {

    List<Order> getByUserId(Long userId);

    List<Order> getByBookId(Long bookId);
}
