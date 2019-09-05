package com.zistone.controller;

import com.zistone.bean.DeviceInfo;
import com.zistone.service.DeviceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/DeviceInfo")
public class DeviceInfoController
{
    Logger logger = LoggerFactory.getLogger(DeviceInfoController.class);

    @Resource
    DeviceInfoService m_deviceInfoService;

    @RequestMapping(value = "/FindAKCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String FindAKCode(@RequestBody DeviceInfo deviceInfo)
    {
        logger.info(">>>收到鉴权请求:" + deviceInfo.getM_akCode());
        return m_deviceInfoService.FindAKCode(deviceInfo.getM_akCode()) + "}\r\n";
    }

    @RequestMapping(value = "/UpdateByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int UpdateByDeviceId(@RequestBody DeviceInfo deviceInfo)
    {
        logger.info(">>>收到更新设备请求:" + deviceInfo.toString());
        return m_deviceInfoService.UpdateDeviceByDeviceId(deviceInfo);
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo Insert(@RequestBody DeviceInfo deviceInfo)
    {
        logger.info(">>>收到新增设备请求:" + deviceInfo.toString());
        //TODO:校验参数
        return m_deviceInfoService.InsertDevice(deviceInfo);
    }

    @RequestMapping(value = "/FindAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<DeviceInfo> FindAllDevice()
    {
        logger.info(">>>收到查询所有设备的请求:");
        return m_deviceInfoService.FindAllDevice();
    }

    @RequestMapping(value = "/FindById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindById(int id)
    {
        logger.info(">>>收到查询设备的请求:" + id);
        return m_deviceInfoService.FindDeviceById(id);
    }

}
