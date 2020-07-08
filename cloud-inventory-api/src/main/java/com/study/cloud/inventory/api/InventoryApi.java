package com.study.cloud.inventory.api;

import com.study.cloud.common.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/inventory")
public interface InventoryApi {
    @RequestMapping(value = "/deduct/{productId}/{userId}/{num}",method = RequestMethod.PUT)
    ResultBean deductInventory(@PathVariable("productId") Long productId,@PathVariable("userId") Long userId, @PathVariable("num") Integer num);
}
