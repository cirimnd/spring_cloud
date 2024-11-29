package com.example.bkmalluser.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId(value = "userid",type = IdType.AUTO)
    private long userid;
    private String userName;
    private String password;
    private String phone;
    private Role   role;




}

//    private orderItem OrderItem;
//待后继实现
