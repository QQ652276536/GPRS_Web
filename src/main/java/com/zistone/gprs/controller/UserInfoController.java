package com.zistone.gprs.controller;

import com.zistone.gprs.bean.UserInfo;
import com.zistone.gprs.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController = @Controller + @ReponseBody
//@Resource默认按byName自动注入
//@Autowired默认按byType自动注入
@RestController
@RequestMapping("/UserInfo")
public class UserInfoController
{
    private Logger _logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    UserInfoService _userInfoService;

    @RequestMapping(value = "/Login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Login(@RequestBody UserInfo userInfo)
    {
        _logger.info(String.format(">>>收到登录请求:%s", userInfo.toString()));
        return _userInfoService.Login(userInfo);
    }

    @RequestMapping(value = "/Register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Register(@RequestBody UserInfo userInfo)
    {
        _logger.info(String.format(">>>收到注册请求:", userInfo.toString()));
        return _userInfoService.Insert(userInfo);
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public UserInfo Update(@RequestBody UserInfo userInfo)
    {
        _logger.info(String.format(">>>收到更新请求:", userInfo.toString()));
        return _userInfoService.Update(userInfo);
    }

}
