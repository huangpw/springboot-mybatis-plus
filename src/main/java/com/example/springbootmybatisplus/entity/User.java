package com.example.springbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author huangpw
 * @date 2024/8/5 15:14
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
@Data
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String city;
}
