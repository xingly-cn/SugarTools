package com.sugar.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sugar.message.entity.Wechat;
import com.sugar.message.mapper.WechatMapper;
import com.sugar.message.service.WechatService;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
public class WechatServiceImpl extends ServiceImpl<WechatMapper, Wechat> implements WechatService {

    public static final String URL = "https://www.pushplus.plus/api/send";

    @Override
    public int sendMsg(Wechat wechat) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(URL);
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("channel","cp"));
        nvps.add(new BasicNameValuePair("content",wechat.getContent()));
        nvps.add(new BasicNameValuePair("template","html"));
        nvps.add(new BasicNameValuePair("title",wechat.getTitle()));
        nvps.add(new BasicNameValuePair("token","510188bc052847c7adb448f45ee39750"));
        nvps.add(new BasicNameValuePair("webhook",wechat.getGroupid()));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"utf-8"));
            client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return baseMapper.insert(wechat);
    }
}
