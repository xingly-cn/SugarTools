package com.sugar.wepay.service.impl;

import com.sugar.wepay.entity.Order;
import com.sugar.wepay.mapper.OrderMapper;
import com.sugar.wepay.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
