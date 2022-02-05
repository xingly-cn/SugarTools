package com.sugar.auth.service.impl;

import com.sugar.auth.entity.User;
import com.sugar.auth.mapper.UserMapper;
import com.sugar.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
