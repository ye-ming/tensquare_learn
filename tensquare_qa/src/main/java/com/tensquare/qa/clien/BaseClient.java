package com.tensquare.qa.clien;

import com.tensquare.qa.clien.impl.BaseClientImpl;
import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yeming
 * @date 2019/8/27 22:44
 */
@FeignClient(value = "tensquare-base",fallback = BaseClientImpl.class)
public interface BaseClient {
    @GetMapping("label/{labelId}")
    Result findById(@PathVariable("labelId") String labelId);
}
