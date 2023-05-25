package com.zhang.service;


import com.zhang.config.UserFacade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp {

    @DubboReference
    private UserFacade userFacade;

    public String getOrder(){
        return userFacade.getUser();
    }
}
