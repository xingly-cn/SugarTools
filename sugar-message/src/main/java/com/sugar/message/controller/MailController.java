package com.sugar.message.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sugar.common.result.R;
import com.sugar.message.entity.Mail;
import com.sugar.message.service.MailService;
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
@RequestMapping("/message/mail")
@CrossOrigin
@Api(tags = "邮件管理")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    @ApiOperation("发送邮件")
    public R sendMessage(@RequestBody Mail mail) {
        int flag = mailService.sendMsg(mail);
        return flag == 0 ? R.error() : R.ok().message("发送成功");
    }

    @GetMapping("/list")
    @ApiOperation("分页查询邮件")
    public R listMessage(String mail, long cur, long size) {
        Page<Mail> page = new Page<>(cur,size);
        QueryWrapper<Mail> wrapper = new QueryWrapper<>();
        wrapper.eq("mail",mail);
        mailService.page(page,wrapper);
        long total = page.getTotal();
        return R.ok().data("mail",mail).data("total",total).data("data",page.getRecords());
    }
}

