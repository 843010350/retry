package com.wwm.retry.controller;


import com.wwm.retry.common.Result;
import com.wwm.retry.retryspring.RetrySpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryTestController {
    @Autowired
    private RetrySpringService retrySpringService;

    @GetMapping("/api/retryTest")
    public Result retryTest(@RequestParam("age") int age){
        int i = retrySpringService.retrySpringTest(age);
        return Result.success();

    }


}
