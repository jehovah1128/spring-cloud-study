package com.study.cloud.credit.api;

import com.study.cloud.common.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/credit")
public interface CreditApi {
    @RequestMapping(value = "/increase/{userId}/{credit}", method = RequestMethod.PUT)
    ResultBean increaseUserCredit(@PathVariable("userId") Long userId,@PathVariable("credit") Integer credit);
}
