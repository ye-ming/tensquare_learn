package com.tensquare.qa.clien.impl;

import com.tensquare.qa.clien.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author yeming
 * @date 2019/9/1 22:05
 */
@Component
public class BaseClientImpl implements BaseClient{
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"執行了熔斷器！！！");
    }
}
