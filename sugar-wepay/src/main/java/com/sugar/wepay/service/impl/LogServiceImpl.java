package com.sugar.wepay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.sugar.wepay.entity.Log;
import com.sugar.wepay.entity.Order;
import com.sugar.wepay.mapper.LogMapper;
import com.sugar.wepay.service.LogService;
import com.sugar.wepay.service.OrderService;
import com.sugar.wepay.utils.WePayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

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

    @Autowired
    private OrderService orderService;

    private static final DecimalFormat df = new DecimalFormat("#");

    @Override
    public Map createOCR(String orderNo, HttpServletRequest request) {
        try{
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order order = orderService.getOne(wrapper);

            SortedMap<String, String> req = new TreeMap<String, String>();
            req.put("appid", "wx5a88f3c48a5f53b3");
            req.put("mch_id", "1603622321");
            req.put("nonce_str", WXPayUtil.generateNonceStr());
            req.put("body", order.getGoodTitle());
            req.put("out_trade_no", orderNo);
            req.put("total_fee", df.format(Double.parseDouble(String.valueOf(order.getTotalFee())) * 100));
            req.put("spbill_create_ip", WePayUtils.getIp(request));
            req.put("notify_url", "https://tool.asugar.cn/wepay/notify");
            req.put("trade_type", "NATIVE");
            req.put("sign", WXPayUtil.generateSignature(req, "5SD1F5W5S56987D2F1G5D6SD5654S66S", WXPayConstants.SignType.MD5));

            String xmlBody = WXPayUtil.generateSignedXml(req, "5SD1F5W5S56987D2F1G5D6SD5654S66S");
            String result = WePayUtils.httpsRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", xmlBody);

            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            Map<String, String> map = new HashMap<>();
            map.put("qr", resultMap.get("code_url"));
            map.put("total_fee", String.valueOf(order.getTotalFee()));
            map.put("orderNo", orderNo);
            map.put("title",order.getGoodTitle());

            return map;
        }catch (Exception e){
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<>();
            map.put("qr","支付订单生成失败");
            return map;
        }
    }

    @Override
    public String queryStatus(String orderNo) {
        try {
            SortedMap<String, String> req = new TreeMap<String, String>();
            req.put("appid", "wx5a88f3c48a5f53b3");
            req.put("mch_id", "1603622321");
            req.put("out_trade_no", orderNo);
            req.put("nonce_str", WXPayUtil.generateNonceStr());

            String xmlBody = null;
            xmlBody = WXPayUtil.generateSignedXml(req, "5SD1F5W5S56987D2F1G5D6SD5654S66S");
            String result = WePayUtils.httpsRequest("https://api.mch.weixin.qq.com/pay/orderquery", "POST", xmlBody);

            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            Map<String, String> map = new HashMap<>();

            if (resultMap == null) {
                return "0";
            }
            if (resultMap.get("trade_state").equals("SUCCESS")) {
                return resultMap.get("transaction_id");
            }
            return "-1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    @Override
    public void undateOrderStatus(String orderNo, String status) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);

        if (order.getStatus() == 0) {
            order.setStatus(1);
            orderService.updateById(order);
        }

        Log log = new Log();
        log.setOrderNo(orderNo);
        log.setPayTime(new Date());
        log.setPayType(1);
        log.setTotalFee(order.getTotalFee());
        log.setTradeState("SUCCESS");
        log.setTransactionId(status);
        log.setAttr("买家：" + order.getNickname() + " 手机：" + order.getMobile());
        baseMapper.insert(log);
    }
}
