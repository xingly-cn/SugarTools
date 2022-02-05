package com.sugar.auth.service;

import com.sugar.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sugar.auth.entity.vo.RegisterVo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-05
 */
public interface UserService extends IService<User> {

    String login(User user);

    String register(RegisterVo registerVo);
}
