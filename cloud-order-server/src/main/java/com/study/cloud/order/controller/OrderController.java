package com.study.cloud.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.cloud.common.ResultBean;
import com.study.cloud.order.api.OrderApi;
import com.study.cloud.order.service.CreditService;
import com.study.cloud.order.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController implements OrderApi {
    @Resource
    private InventoryService inventoryService;
    @Resource
    private CreditService creditService;

    @Override
    public ResultBean placeOrder(@PathVariable("userId") Long userId,
                                 @PathVariable("productId") Long productId,
                                 @PathVariable("num") Integer num) {
        log.info("新增订单,用户ID为 {} ,产品ID为 {} ,数量为 {} ",userId,productId,num);
        ResultBean inventoryResult = inventoryService.deductInventory(productId, userId, num);
        log.info("inventoryResult:{}", JSONObject.toJSONString(inventoryResult));
        ResultBean creditResult = creditService.increaseUserCredit(userId, 100);
        log.info("creditResult:{}", JSONObject.toJSONString(creditResult));
        return ResultBean.success().message("用户下单成功");
    }
}
