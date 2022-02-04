package com.sugar.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sugar.message.entity.Wechat;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
public interface WechatService extends IService<Wechat> {

    int sendMsg(Wechat wechat);
}
