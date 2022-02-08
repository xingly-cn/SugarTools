package com.sugar.wepay.controller;


import com.sugar.common.result.R;
import com.sugar.wepay.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
@RestController
@RequestMapping("/wepay/log")
@CrossOrigin
@Api(tags = "支付管理")
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/createQR")
    @ApiOperation("支付二维码")
    public R createOCR(@RequestParam("orderNo") String orderNo, HttpServletRequest request) {
        Map map = logService.createOCR(orderNo, request);
        return R.ok().data(map);
    }


}

