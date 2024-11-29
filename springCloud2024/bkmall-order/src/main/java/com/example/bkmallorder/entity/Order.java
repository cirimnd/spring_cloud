package com.example.bkmallorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 书本ID
     */
    private Long bookId;

    /**
     * 书本价格
     */
    private Double bookPrice;

    /**
     * 0表示但未支付, 1表示支付了
     */
    private Integer state;

    /**
     * 下单日期, 时分秒
     */
    private LocalDateTime orderDatetime;

    /**
     * 地址
     */
    private String address;


}
