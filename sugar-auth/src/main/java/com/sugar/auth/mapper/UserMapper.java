package com.sugar.auth.mapper;

import com.sugar.auth.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author 方糖
 * @since 2022-02-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
