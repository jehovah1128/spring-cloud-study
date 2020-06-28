package com.study.cloud.inventory.controller;

import com.alibaba.fastjson.JSONObject;
import com.study.cloud.common.ResultBean;
import com.study.cloud.inventory.api.InventoryApi;
import com.study.cloud.inventory.service.WmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class InventoryController implements InventoryApi {
    @Resource
    private WmsService wmsService;
    @Override
    public ResultBean deductInventory(@PathVariable("productId")Long productId,@PathVariable("userId") Long userId, @PathVariable("num") Integer num) {
        log.info("对product id 为 {} 的商品进行库存减少 {} 的操作",productId,num);
        ResultBean wmsResult =  wmsService.noticeWmsSend(productId, userId, num);
        log.info("creditResult:{}", JSONObject.toJSONString(wmsResult));
        return ResultBean.success().message("减少库存成功");
    }
}
