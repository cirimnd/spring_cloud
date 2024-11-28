package org.example.entity;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("book")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "userid",type = IdType.AUTO)
    private long userid;

    private String UserName;
    private String Password;
    private String Phone;
    private Role   role;




}

//    private orderItem OrderItem;
//待后继实现
