package com.example.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootmybatisplus.entity.User;

import java.util.List;

/**
 * @author huangpw
 * @date 2024/8/5 15:29
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
public interface UserService extends IService<User> {
    Page<User> page(Integer current, Integer size, String username);

    boolean saveOrUpdateById(User user);

    boolean deleteBatchIds(List<Integer> ids);
}
