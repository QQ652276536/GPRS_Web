package com.zistone.controller;

import com.zistone.bean.UserInfo;
import com.zistone.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoController
{
    Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    UserInfoService m_userInfoService;

    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Login(@RequestBody UserInfo userInfo)
    {
        logger.info(">>>收到登录请求:" + userInfo.toString());
        //TODO:校验参数
        return m_userInfoService.Login(userInfo);
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Register(@RequestBody UserInfo userInfo)
    {
        logger.info(">>>收到注册请求:" + userInfo.toString());
        //TODO:校验参数
        return m_userInfoService.Insert(userInfo);
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Update(@RequestBody UserInfo userInfo)
    {
        logger.info(">>>收到更新请求:" + userInfo.toString());
        //TODO:校验参数
        return m_userInfoService.Update(userInfo);
    }
}
