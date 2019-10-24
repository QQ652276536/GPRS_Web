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
    private Logger m_logger = LoggerFactory.getLogger(DeviceInfoController.class);

    @Resource
    DeviceInfoService m_deviceInfoService;

    @RequestMapping(value = "/FindByAKCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindByAKCode(@RequestBody DeviceInfo deviceInfo)
    {
        String akCode = deviceInfo.getM_akCode();
        m_logger.info(">>>收到鉴权请求:" + akCode);
        return m_deviceInfoService.FindByAKCode(akCode);
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo Update(@RequestBody DeviceInfo deviceInfo)
    {
        m_logger.info(">>>收到更新设备请求:" + deviceInfo.toString());
        return m_deviceInfoService.Update(deviceInfo);
    }

    @RequestMapping(value = "/UpdateLocationByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int UpdateLocationByDeviceId(@RequestBody DeviceInfo deviceInfo)
    {
        m_logger.info(">>>收到更新设备位置信息的请求:" + deviceInfo.toString());
        return m_deviceInfoService.UpdateLocationByDeviceId(deviceInfo);
    }

    @RequestMapping(value = "/InsertByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo InsertByDeviceId(@RequestBody DeviceInfo deviceInfo)
    {
        m_logger.info(">>>收到新增设备请求:" + deviceInfo.toString());
        return m_deviceInfoService.InsertByDeviceId(deviceInfo);
    }

    @RequestMapping(value = "/FindAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<DeviceInfo> FindAllDevice()
    {
        m_logger.info(">>>收到查询所有设备的请求:");
        return m_deviceInfoService.FindAllDevice();
    }

}
