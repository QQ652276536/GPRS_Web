package com.zistone.controller;

import com.zistone.bean.UserInfo;
import com.zistone.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {

    Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    UserInfoService m_userInfoService;

    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Login(@RequestBody UserInfo userInfo) {
        logger.info("收到登录请求,参数是:" + userInfo.toString());
        System.out.println(userInfo.getM_craeteTime());
        return m_userInfoService.Login(userInfo);
    }

    @RequestMapping(value = "/UserInfo/Register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Register(@RequestBody UserInfo userInfo) {
        logger.info("收到注册请求,参数是:" + userInfo.toString());

        return "1";
    }
}
