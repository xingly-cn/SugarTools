package com.sugar.wepay.service;

import com.sugar.wepay.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
public interface LogService extends IService<Log> {

    Map createOCR(String orderNo, HttpServletRequest request);

    String queryStatus(String orderNo);

    void undateOrderStatus(String orderNo, String status);
}
