package com.example.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootmybatisplus.entity.User;
import com.example.springbootmybatisplus.mapper.UserMapper;
import com.example.springbootmybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangpw
 * @date 2024/8/5 15:30
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> page(Integer current, Integer size, String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 降序排序
        wrapper.orderByDesc(User::getId);
        // 推荐写法
        if(!"".equals(username)) {
            //精确查询
            //wrapper.eq(User::getUsername, username);
            //模糊查询
            wrapper.like(User::getUsername, username);
        }
        Page<User> page = page(new Page<>(current, size), wrapper);
        return page;
    }

    @Override
    public boolean saveOrUpdateById(User user) {
        if(user.getId() != null) {
            return updateById(user);
        } else {
            return save(user);
        }
    }

    @Override
    public boolean deleteBatchIds(List<Integer> ids) {
        return removeBatchByIds(ids);
    }
}
