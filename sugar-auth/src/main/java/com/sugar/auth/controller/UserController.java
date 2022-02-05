package com.sugar.auth.controller;


import com.sugar.auth.entity.User;
import com.sugar.auth.service.UserService;
import com.sugar.common.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-05
 */
@RestController
@RequestMapping("/auth/user")
@CrossOrigin
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登陆")
    public R login(@RequestBody User user) {
        String token = userService.login(user);
        return R.ok().data("token",token);
    }


}

