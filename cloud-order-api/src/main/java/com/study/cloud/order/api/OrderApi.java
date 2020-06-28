package com.study.cloud.order.api;

import com.study.cloud.common.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/order")
public interface OrderApi {
    @RequestMapping(value = "/place/{userId}/{productId}/{num}",method = RequestMethod.POST)
    ResultBean placeOrder(@PathVariable("userId") Long userId,
                          @PathVariable("productId") Long productId,
                          @PathVariable("num") Integer num);
}
