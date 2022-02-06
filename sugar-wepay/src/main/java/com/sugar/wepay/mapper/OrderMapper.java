package com.sugar.wepay.mapper;

import com.sugar.wepay.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
