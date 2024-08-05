package com.example.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootmybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangpw
 * @date 2024/8/5 15:17
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
