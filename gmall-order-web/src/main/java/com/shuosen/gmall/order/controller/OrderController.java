package com.shuosen.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shuosen.gmall.bean.UserAddress;
import com.shuosen.gmall.service.Userservice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    /***
     * 要使用dubbo的reference来调用服务
     */
    @Reference
    private Userservice userservice ;

    @RequestMapping("trade")
    @ResponseBody
    public List<UserAddress> trade(String userId){
        System.out.println("消费端-----------------------");
        //调用服务查询用户地址列表
        return  userservice.findAdressByUserId(userId);
    }
}
