package com.zistone.controller;

import com.zistone.bean.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/UserInfo")
public class UserInfoController {

    Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping(value = "/Register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Register(@RequestBody UserInfo userInfo) {
        logger.info("收到注册请求,参数是:"+userInfo.toString());


        return "1";
    }
}
