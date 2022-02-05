package com.sugar.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.auth.entity.User;
import com.sugar.auth.entity.vo.RegisterVo;
import com.sugar.auth.mapper.UserMapper;
import com.sugar.auth.service.UserService;
import com.sugar.common.utils.JwtUtils;
import com.sugar.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String login(User user) {
        String mobile = user.getMobile();
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));

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

    @Override
    public String register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = DigestUtils.md5DigestAsHex(registerVo.getPassword().getBytes(StandardCharsets.UTF_8));


        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname)) {
            return "信息不可为空";
        }

        String codeRedis= redisUtil.get(mobile).toString();
        if (!code.equals(codeRedis)) {
            return "验证码错误";
        }

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            return "手机号已存在";
        }

        User user = new User();
        BeanUtils.copyProperties(registerVo,user);
        user.setPassword(password);
        user.setIsDisabled(false);

        int insert = baseMapper.insert(user);
        if (insert == 0) {
            return "注册失败";
        }

        return "注册成功";
    }
}
