package com.zhang.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private RestTemplate restTemplate;

    public String getOrder(){
        return restTemplate.getForObject("http://localhost:8080/user/getUser",String.class);
    }
}
