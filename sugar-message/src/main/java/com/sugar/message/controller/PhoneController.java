package com.sugar.message.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.common.result.PhoneVo;
import com.sugar.common.result.R;
import com.sugar.message.entity.Phone;
import com.sugar.message.service.PhoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //TODO 发送短信,对手机号进行正则校验,对短信内容进行关键词过滤
    @PostMapping("/send")
    @ApiOperation("发送短信")
    public R sendMessage(@RequestBody Phone phone) {
        int flag = phoneService.sendMsg(phone);
        return flag == 0 ? R.error() : R.ok().message("发送成功");
    }

    @GetMapping("/list")
    @ApiOperation("分页查询短信")
    public R listMessage(String phone, long cur, long size) {
        Page<Phone> page = new Page<>(cur,size);
        QueryWrapper<Phone> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        phoneService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("phone",phone).data("total",total).data("data",page.getRecords());
    }


    //TODO 需要改成查询 sugar-good信息
    @GetMapping("/getGoodInfo")
    @ApiOperation("根据ID查询商品信息")
    public PhoneVo getGoodInfo(String goodId) {
        Phone phone = phoneService.getById(goodId);
        PhoneVo phoneVo = new PhoneVo();
        BeanUtils.copyProperties(phone,phoneVo);
        return phoneVo;
    }


}

