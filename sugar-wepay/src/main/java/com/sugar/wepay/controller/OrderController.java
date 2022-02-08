package com.sugar.wepay.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sugar.common.result.R;
import com.sugar.common.utils.JwtUtils;
import com.sugar.wepay.entity.Order;
import com.sugar.wepay.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/wepay/order")
@Api(tags = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //TODO 稍后会增加商品管理 sugar-good
    @PostMapping("/createOrder")
    @ApiOperation("生成订单")
    public R createOrder(@RequestParam("goodId") String goodId, HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(goodId,userId);
        return R.ok().data("orderNo",orderNo);
    }

    @GetMapping("/getOrderInfo")
    @ApiOperation("查询订单")
    public R getOrderInfo(String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }

}

