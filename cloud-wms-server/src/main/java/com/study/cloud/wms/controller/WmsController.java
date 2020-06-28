package com.study.cloud.wms.controller;

import com.study.cloud.common.ResultBean;
import com.study.cloud.wms.api.WmsApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WmsController implements WmsApi {
    @Override
    public ResultBean noticeWmsSend(@PathVariable("productId") Long productId,
                                    @PathVariable("userId") Long userId,
                                    @PathVariable("num") Integer num) {
        log.info("对ID为 {} 的用户,准备发送product ID 为 {} 的物品 共 {} 件",userId,productId,num);
        return ResultBean.success().message("通知发货成功");
    }
}
