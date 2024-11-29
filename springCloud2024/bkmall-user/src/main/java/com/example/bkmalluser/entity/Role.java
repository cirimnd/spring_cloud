package com.example.bkmalluser.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN("ADMIN","管理员"),
    USER("USER","用户");

    @EnumValue
    private final String role;//标记为数据库中存储的
    @JsonValue
    private final String description;//标记为返回的

    Role(String role , String description) {
        this.role = role;
        this.description = description;
    }

    public String getRole() {return role;}

    public String getDescription() {
        return description;
    }
}
