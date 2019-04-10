package com.shuosen.gmall.service;

import com.shuosen.gmall.bean.UserAddress;
import com.shuosen.gmall.bean.UserInfo;

import java.util.List;

public interface Userservice {

    //获取所有的用户信息
    List<UserInfo> findAll();

    //根据userId获取地址
    List<UserAddress> findAdressByUserId(String userId);

}
