package com.zistone.controller;

import com.zistone.bean.DeviceInfo;
import com.zistone.service.DeviceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/DeviceInfo")
public class DeviceInfoController
{

    Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    @Resource
    DeviceInfoService m_deviceInfoService;

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Insert(@RequestBody DeviceInfo deviceInfo)
    {
        logger.info("收到新增设备请求,参数是:" + deviceInfo.toString());
        //TODO:校验参数
        //<<<用于表示Socket发Http请求时的结束标识符
        return m_deviceInfoService.InsertDevice(deviceInfo) + "<<<\r\n";
    }

    @RequestMapping(value = "/Delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String Delete(@RequestParam int id)
    {
        logger.info("收到删除设备请求,参数是:" + id);
        m_deviceInfoService.DelDeviceById(id);
        return "success";
    }

    @RequestMapping(value = "/FindAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<DeviceInfo> FindAllDevice()
    {
        return m_deviceInfoService.FindAllDevice();
    }

    @RequestMapping(value = "/FindById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindById(int id)
    {
        return m_deviceInfoService.FindDeviceById(id);
    }

    @RequestMapping(value = "/FindByName", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindByName(String name)
    {
        return m_deviceInfoService.FindDeviceByName(name);
    }

    @RequestMapping(value = "/FindByNameAndId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindByNameAndId(String name, int id)
    {
        return m_deviceInfoService.FindDeviceByNameAndId(name, id);
    }

}
