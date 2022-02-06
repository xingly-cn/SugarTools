package com.sugar.wepay.service.impl;

import com.sugar.wepay.entity.Log;
import com.sugar.wepay.mapper.LogMapper;
import com.sugar.wepay.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-06
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
