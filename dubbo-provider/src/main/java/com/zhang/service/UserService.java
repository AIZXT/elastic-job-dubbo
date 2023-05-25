package com.zhang.service;


import com.zhang.config.UserFacade;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


@DubboService
@Component
public class UserService implements UserFacade {

    @Override
    public String getUser(){
        return "调到了";
    }
}
