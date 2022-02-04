package com.sugar.message.controller;


import com.sugar.common.result.R;
import com.sugar.message.service.PhoneService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
@RestController
@RequestMapping("/message/phone")
@CrossOrigin
@Api(tags = "短信管理")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/send")
    public R sendMessage(String phone) {
        String code = String.format("%04d",new Random().nextInt(9999));
        return R.ok().data("yzm",code);
    }

}

