package com.example.bkmalluser.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.example.bkmalluser.entity.User;
import com.example.bkmalluser.mapper.UserMapper;
import com.example.bkmalluser.service.UserService;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
