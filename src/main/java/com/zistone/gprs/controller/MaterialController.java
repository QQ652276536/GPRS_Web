package com.zistone.gprs.controller;

import com.alibaba.fastjson.JSON;
import com.zistone.gprs.bean.Material;
import com.zistone.gprs.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/Material")
public class MaterialController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaterialController.class);

    //@Resource默认按byName自动注入
    //@Autowired默认按byType自动注入
    @Resource
    MaterialService _materialService;

    @RequestMapping(value = "/Update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Material Update(@RequestBody String jsonData) {
        Material material = JSON.parseObject(jsonData, Material.class);
        LOGGER.info(String.format("收到绑定物料的请求:%s", material.toString()));
        return _materialService.Update(material);
    }

    @RequestMapping(value = "/FindByDeviceAddress", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Material FindByDeviceAddress(@RequestParam("deviceAddress") String deviceAddress) {
        LOGGER.info(String.format("收到查询物料的请求:%s", deviceAddress));
        return _materialService.FindByDeviceAddress(deviceAddress);
    }

}
