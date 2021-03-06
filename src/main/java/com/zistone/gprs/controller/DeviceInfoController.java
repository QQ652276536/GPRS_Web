package com.zistone.gprs.controller;

import com.alibaba.fastjson.JSON;
import com.zistone.gprs.bean.DeviceInfo;
import com.zistone.gprs.service.DeviceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/DeviceInfo")
public class DeviceInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoController.class);

    //@Resource默认按byName自动注入
    //@Autowired默认按byType自动注入
    @Resource
    DeviceInfoService _deviceInfoService;

    @RequestMapping(value = "/FindByAKCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo FindByAKCode(@RequestBody String jsonData) {
        DeviceInfo deviceInfo = JSON.parseObject(jsonData, DeviceInfo.class);
        String akCode = deviceInfo.getAkCode();
        LOGGER.info(String.format("收到设备%s的鉴权请求:%s", deviceInfo.getDeviceId(), akCode));
        return _deviceInfoService.FindByAKCode(akCode);
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public DeviceInfo Update(@RequestBody String jsonData) {
        DeviceInfo deviceInfo = JSON.parseObject(jsonData, DeviceInfo.class);
        LOGGER.info(String.format("收到更新设备的请求:%s", deviceInfo.toString()));
        return _deviceInfoService.Update(deviceInfo);
    }

    @RequestMapping(value = "/UpdateLocationByDeviceId", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public int UpdateLocationByDeviceId(@RequestBody DeviceInfo deviceInfo) {
        LOGGER.info(String.format("收到更新设备位置的请求:%s", deviceInfo.toString()));
        return _deviceInfoService.UpdateLocationByDeviceId(deviceInfo);
    }

    @RequestMapping(value = "/InsertByDeviceId", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public DeviceInfo InsertByDeviceId(@RequestBody DeviceInfo deviceInfo) {
        LOGGER.info(String.format("收到新增设备的请求:%s", deviceInfo.toString()));
        return _deviceInfoService.InsertByDeviceId(deviceInfo);
    }

    @RequestMapping(value = "/FindAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<DeviceInfo> FindAllDevice() {
        LOGGER.info("收到查询所有设备的请求:");
        List<DeviceInfo> result = _deviceInfoService.FindAllDevice();
        result.forEach(v -> {
            LOGGER.info(v.toString());
        });
        return result;
    }

}
