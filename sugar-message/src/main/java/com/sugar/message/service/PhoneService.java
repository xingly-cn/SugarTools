package com.sugar.message.service;

import com.sugar.message.entity.Phone;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
public interface PhoneService extends IService<Phone> {

    int sendMsg(Phone phone);

}
