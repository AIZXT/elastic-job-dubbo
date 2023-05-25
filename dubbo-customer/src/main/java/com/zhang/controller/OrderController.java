package com.zhang.controller;


import com.zhang.service.OrderService;
import com.zhang.service.OrderServiceImp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private OrderServiceImp orderServiceImp;


    @GetMapping("/getOrder")
    public String getOrder(){
        return orderService.getOrder() + "User服务";
    }

    @GetMapping("/getOrderDubbo")
    public String getOrderDubbo(){
        System.out.println("###############################");
        return orderServiceImp.getOrder() + "User服务forDubbo框架";
    }
}
