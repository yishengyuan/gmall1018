package com.shuosen.gmall.user.controller;

import com.shuosen.gmall.bean.UserInfo;
import com.shuosen.gmall.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private Userservice userservice ;

    @RequestMapping("findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
        return userservice.findAll();
    }
}
