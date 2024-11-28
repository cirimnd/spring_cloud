package com.example.bkmallorder.mapper;

import com.example.bkmallorder.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
