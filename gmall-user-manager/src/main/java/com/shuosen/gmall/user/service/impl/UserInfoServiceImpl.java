package com.shuosen.gmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shuosen.gmall.bean.UserAddress;
import com.shuosen.gmall.bean.UserInfo;
import com.shuosen.gmall.service.Userservice;
import com.shuosen.gmall.user.mapper.UserAddressMapper;
import com.shuosen.gmall.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserInfoServiceImpl implements Userservice {

    @Autowired
    private UserInfoMapper userInfoMapper ;

    @Autowired
    private UserAddressMapper userAddressMapper ;

    @Override
    public List<UserInfo> findAll() {

        return  userInfoMapper.selectAll();
    }

    @Override
    public List<UserAddress> findAdressByUserId(String userId) {
        System.out.println("生产者-----------------------");
        UserAddress address = new UserAddress() ;
        address.setUserId(userId);
        return userAddressMapper.select(address);
    }
}
