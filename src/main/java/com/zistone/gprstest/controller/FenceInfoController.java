package com.zistone.gprstest.controller;

import com.zistone.gprstest.bean.FenceInfo;
import com.zistone.gprstest.service.FenceInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/FenceInfo")
public class FenceInfoController
{
    private Logger m_logger = LoggerFactory.getLogger(FenceInfoController.class);

    @Autowired
    FenceInfoService m_fenceInfoService;

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<FenceInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId)
    {
        m_logger.info(">>>收到查询" + deviceId + "所有围栏的请求");
        return m_fenceInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/InsertByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public FenceInfo InsertByDeviceId(@RequestBody FenceInfo fenceInfo)
    {
        m_logger.info(">>>收到新增围栏请求:" + fenceInfo.toString());
        return m_fenceInfoService.InsertByDeviceId(fenceInfo);
    }

    @RequestMapping(value = "/DelById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public void DelById(@RequestParam("id") String id)
    {
        m_logger.info(String.format(">>>收到删除围栏%s的请求:", id));
        m_fenceInfoService.DelById(id);
    }

}
