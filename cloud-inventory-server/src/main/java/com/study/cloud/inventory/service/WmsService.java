package com.study.cloud.inventory.service;

import com.study.cloud.wms.api.WmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "wms-server")
public interface WmsService extends WmsApi {
}
