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
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
            req.put("appid", "wx5a88f3c48a5f53b3");    //公众号
            req.put("mch_id", "1603622321");  // 商户号
            req.put("nonce_str", WXPayUtil.generateNonceStr()); // 32位随机字符串
            req.put("body", order.getGoodTitle()); // 商品描述
            req.put("out_trade_no", orderNo);   // 商户订单号
            req.put("total_fee", df.format(Double.parseDouble(String.valueOf(order.getTotalFee())) * 100));    // 标价金额(分)
            req.put("spbill_create_ip", WePayUtils.getIp(request));   // 终端IP
            req.put("notify_url", "https://tool.asugar.cn/wepay/notify");  // 回调地址
            req.put("trade_type", "NATIVE");    // 交易类型
            req.put("sign", WXPayUtil.generateSignature(req, "5SD1F5W5S56987D2F1G5D6SD5654S66S", WXPayConstants.SignType.MD5));


            //生成要发送的 xml
            String xmlBody = WXPayUtil.generateSignedXml(req, "5SD1F5W5S56987D2F1G5D6SD5654S66S");
            String result = WePayUtils.httpsRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", xmlBody);

            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            Map<String, String> map = new HashMap<>();
            map.put("qr", resultMap.get("code_url")); // 支付地址
            map.put("total_fee", String.valueOf(order.getTotalFee())); // 总金额
            map.put("orderNo", orderNo);    // 订单号
            map.put("title",order.getGoodTitle());
            return map;
        }catch (Exception e){
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<>();
            map.put("qr","支付订单生成失败");
            return map;
        }
    }
}
