package com.example.springbootmybatisplus.controller;

import com.example.springbootmybatisplus.entity.User;
import com.example.springbootmybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangpw
 * @date 2024/8/5 15:19
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public List<User> list() {
        return userMapper.selectList(null);
    }
}
