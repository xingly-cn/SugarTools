package com.sugar.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.auth.entity.User;
import com.sugar.auth.mapper.UserMapper;
import com.sugar.auth.service.UserService;
import com.sugar.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
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


    @Override
    public String login(User user) {
        String mobile = user.getMobile();
        String password = user.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return "用户名或密码为空";
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        User userSQL = baseMapper.selectOne(wrapper);
        if (userSQL == null) {
            return "用户不存在";
        }

        if (userSQL.getIsDeleted()) {
            return "用户已删除";
        }

        if (!password.equals(userSQL.getPassword())) {
            return "用户名或密码错误";
        }

        if (userSQL.getIsDisabled()) {
            return "用户被禁用";
        }

        String jwtToken = JwtUtils.getJwtToken(userSQL.getId(), userSQL.getNickname());
        return jwtToken;
    }
}
