package com.telincn.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.telincn.domain.UserInfoRepository;
import com.telincn.entity.UserInfo;
import com.telincn.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
   
    @Resource
    private UserInfoRepository userInfoRepository;
   
    @Override
    public UserInfo findByUsername(String username) {
       System.out.println("UserInfoServiceImpl.findByUsername()");
       return userInfoRepository.findByUsername(username);
    }
   
}
