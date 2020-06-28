package com.study.cloud.wms.api;

import com.study.cloud.common.ResultBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/wms")
public interface WmsApi {
    @RequestMapping(value = "/notice/{productId}/{userId}/{num}",method = RequestMethod.POST)
    ResultBean noticeWmsSend(@PathVariable("productId") Long productId,
                             @PathVariable("userId") Long userId,
                             @PathVariable("num") Integer num);
}
