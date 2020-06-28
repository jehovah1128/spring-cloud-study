package com.study.cloud.order.service;

import com.study.cloud.inventory.api.InventoryApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "inventory-server")
public interface InventoryService extends InventoryApi {
}
