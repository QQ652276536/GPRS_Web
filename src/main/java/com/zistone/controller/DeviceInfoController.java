package com.zistone.controller;

import com.zistone.bean.DeviceInfo;
import com.zistone.service.DeviceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/DeviceInfo")
public class DeviceInfoController {

    Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    @Resource
    DeviceInfoService m_deviceInfoService;

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Insert(@RequestBody DeviceInfo deviceInfo) {
        logger.info("收到新增设备请求,参数是:" + deviceInfo.toString());
        //TODO:校验参数
        return m_deviceInfoService.InsertDevice(deviceInfo);
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Delete(@RequestParam int id) {
        logger.info("收到删除设备请求,参数是:" + id);
        //TODO:校验参数
        m_deviceInfoService.DelDeviceById(id);
        return "success";
    }
}
