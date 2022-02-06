package com.sugar.wepay.feign;

import com.sugar.common.result.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("sugar-auth")
public interface AuthFeign {

    @GetMapping("/auth/user/info2")
    public UserVo info2(@RequestParam("userId") String userId);
}
