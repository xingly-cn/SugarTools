package com.sugar.message.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.message.entity.Phone;
import com.sugar.message.mapper.PhoneMapper;
import com.sugar.message.service.PhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {
    @Override
    public int sendMsg(Phone phone) {
        //TODO 发送短信业务
        return baseMapper.insert(phone);
    }

}
