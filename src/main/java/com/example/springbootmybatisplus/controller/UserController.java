package com.example.springbootmybatisplus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmybatisplus.entity.User;
import com.example.springbootmybatisplus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huangpw
 * @date 2024/8/5 15:49
 * 博客： https://blog.csdn.net/qq_18167779?type=blog
 * @description:
 */
//第一种跨域解决方式
@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 分页列表 模糊查询
     * @param username
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/page")
    public Page<User> page(
//            @RequestParam("username") String username,
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "15") Integer size
    ){

        return userService.page(current,size,username);

    }

    /**
     * 数据保存和新增
     * @param user
     * @return
     */
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return  userService.saveOrUpdateById(user);
    }

    /**
     * 单选删除和批量删除
     * @param ids
     * @return
     */
    @PostMapping("/delete")
    public boolean delete(@RequestBody List<Integer> ids){
        return userService.deleteBatchIds(ids);
    }
}
