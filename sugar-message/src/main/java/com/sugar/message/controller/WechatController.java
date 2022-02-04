package com.sugar.message.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.common.result.R;
import com.sugar.message.entity.Wechat;
import com.sugar.message.service.WechatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/message/wechat")
@CrossOrigin
@Api(tags = "微信通知")
public class WechatController {

    @Autowired
    private WechatService wechatService;

    @PostMapping("/send")
    @ApiOperation("发送通知")
    public R sendMessage(@RequestBody Wechat wechat) {
        int flag = wechatService.sendMsg(wechat);
        return flag == 0 ? R.error() : R.ok().message("发送成功");
    }

    @GetMapping("/list")
    @ApiOperation("分页查询通知")
    public R listMessage(String groupId, long cur, long size) {
        Page<Wechat> page = new Page<>(cur,size);
        QueryWrapper<Wechat> wrapper = new QueryWrapper<>();
        wrapper.eq("groupid",groupId);
        wechatService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("groupId",groupId).data("total",total).data("data",page.getRecords());
    }

}

