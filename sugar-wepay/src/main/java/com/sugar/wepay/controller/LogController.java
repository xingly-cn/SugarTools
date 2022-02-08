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

    @GetMapping("/status")
    @ApiOperation("支付状态")
    public R status(String orderNo) {
        String status = logService.queryStatus(orderNo);
        if (status.equals("-1")) return R.error().message("支付中...");
        if (status.length() == 28) {
            logService.undateOrderStatus(orderNo, status);
            return R.ok().data("status",status);
        }
        return R.error().message("支付失败");
    }

}

