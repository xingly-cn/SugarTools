package com.sugar.message.service.impl;

import com.sugar.message.entity.Mail;
import com.sugar.message.mapper.MailMapper;
import com.sugar.message.service.MailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 方糖
 * @since 2022-02-04
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public int sendMsg(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("shukayun@xingly.cn");
        //TODO 多个抄送人处理，people的传参用逗号分割，组成字符串列表
        if (mail.getPeople().equals("")) {
            message.setTo(mail.getMail());
        }else {
            message.setTo(mail.getMail(),mail.getPeople());
        }
        message.setSubject(mail.getTitle());
        message.setText(mail.getContent());
        mailSender.send(message);
        return baseMapper.insert(mail);
    }
}
