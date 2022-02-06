package com.sugar.wepay.service.impl;

import com.sugar.common.result.PhoneVo;
import com.sugar.common.result.UserVo;
import com.sugar.wepay.entity.Order;
import com.sugar.wepay.feign.AuthFeign;
import com.sugar.wepay.feign.MessageFeign;
import com.sugar.wepay.mapper.OrderMapper;
import com.sugar.wepay.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

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

    @Autowired
    private MessageFeign messageFeign;

    @Autowired
    private AuthFeign authFeign;

    //TODO 需要修改
    @Override
    public String createOrder(String goodId, String userId) {
        // 远程调用 sugar-auth 获取用户信息
        UserVo userVo = authFeign.info2(userId);

        // 远程调用 sugar-good 获取商品信息,目前先用 sugar-message 代替一下
        PhoneVo goodInfo = messageFeign.getGoodInfo(goodId);

        Order order = new Order();
        String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
        order.setOrderNo(orderNo);
        //-------------------需要修改
        order.setGoodId(goodInfo.getId());
        order.setGoodTitle(goodInfo.getContent());
        //-------------------
        order.setMemberId(userVo.getId());
        order.setNickname(userVo.getNickname());
        order.setMobile(userVo.getMobile());
        order.setTotalFee(BigDecimal.valueOf(9.9));
        order.setPayType(1);
        order.setStatus(0);
        baseMapper.insert(order);

        return orderNo;
    }
}
