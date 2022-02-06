package com.sugar.wepay.feign;

import com.sugar.common.result.PhoneVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("sugar-message")
public interface MessageFeign {

    @GetMapping("/message/phone/getGoodInfo")
    public PhoneVo getGoodInfo(@RequestParam("goodId") String goodId);

}
