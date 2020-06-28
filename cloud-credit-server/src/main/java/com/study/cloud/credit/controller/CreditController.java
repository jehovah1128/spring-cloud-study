package com.study.cloud.credit.controller;

import com.study.cloud.common.ResultBean;
import com.study.cloud.credit.api.CreditApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreditController implements CreditApi {
    @Override
    public ResultBean increaseUserCredit(@PathVariable("userId") Long userId,@PathVariable("credit") Integer credit) {
        log.info("对ID为 {} 的用户进行增加 {} 积分的操作",userId,credit);
        return ResultBean.success().message("增加用户积分成功");
    }
}
