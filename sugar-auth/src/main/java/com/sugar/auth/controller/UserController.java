package com.sugar.auth.controller;


import com.sugar.auth.entity.User;
import com.sugar.auth.entity.vo.RegisterVo;
import com.sugar.auth.service.UserService;
import com.sugar.common.result.R;
import com.sugar.common.result.UserVo;
import com.sugar.common.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public R register(@RequestBody RegisterVo registerVo) {
        String info = userService.register(registerVo);
        return R.ok().message(info);
    }

    @GetMapping("/info")
    @ApiOperation("用户信息")
    public R info(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        User user = userService.getById(userId);
        user.setPassword(null);
        user.setGmtCreate(null);
        user.setGmtModified(null);
        return R.ok().data("user",user);
    }

    @GetMapping("/info2")
    @ApiOperation("用户信息2")
    public UserVo info2(String userId) {
        User user = userService.getById(userId);
        user.setPassword(null);
        user.setGmtCreate(null);
        user.setGmtModified(null);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
}

