package com.study.cloud.order.service;

import com.study.cloud.credit.api.CreditApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "credit-server")
public interface CreditService extends CreditApi {
}
