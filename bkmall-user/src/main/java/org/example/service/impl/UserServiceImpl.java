package org.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
}
