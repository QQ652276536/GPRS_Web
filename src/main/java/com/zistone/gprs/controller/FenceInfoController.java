package com.zistone.gprs.controller;

import com.zistone.gprs.bean.FenceInfo;
import com.zistone.gprs.service.FenceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/FenceInfo")
public class FenceInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenceInfoController.class);

    //@Resource默认按byName自动注入
    //@Autowired默认按byType自动注入
    @Resource
    FenceInfoService _fenceInfoService;

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<FenceInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId) {
        LOGGER.info(String.format("收到查询设备%s所有围栏的请求:", deviceId));
        return _fenceInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/InsertByDeviceId", method = RequestMethod.POST, produces = "application/json;" +
            "charset=UTF-8")
    public FenceInfo InsertByDeviceId(@RequestBody FenceInfo fenceInfo) {
        LOGGER.info(String.format("收到新增围栏请求:%s", fenceInfo.toString()));
        return _fenceInfoService.InsertByDeviceId(fenceInfo);
    }

    @RequestMapping(value = "/DelById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void DelById(@RequestParam("id") String id) {
        LOGGER.info(String.format("收到删除围栏%s的请求:", id));
        _fenceInfoService.DelById(id);
    }

}
