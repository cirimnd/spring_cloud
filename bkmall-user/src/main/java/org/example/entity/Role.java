package org.example.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum Role {
    @EnumValue
    ADMIN("管理员"),
    @EnumValue
    USER("用户");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
