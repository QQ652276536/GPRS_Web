package com.zistone.controller;

import com.zistone.bean.LocationInfo;
import com.zistone.service.LocationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/LocationInfo")
public class LocationInfoController
{
    private Logger m_logger = LoggerFactory.getLogger(LocationInfoController.class);

    @Resource
    LocationInfoService m_locationInfoService;

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST)
    public List<LocationInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId)
    {
        m_logger.info(">>>收到查询设备所有的历史轨迹请求:" + deviceId);
        return m_locationInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/FindByDeviceIdAndBetweenTime", method = RequestMethod.POST)
    public List<LocationInfo> FindByDeviceIdAndBetweenTime(
            @RequestParam("deviceId") String deviceId, @RequestParam("startTime") long startTime, @RequestParam("endTime") long endTime)
    {
        m_logger.info(">>>收到查询设备指定日期的历史轨迹请求:" + deviceId + "开始时间:" + startTime + "结束时间:" + endTime);
        return m_locationInfoService.FindByDeviceIdAndTime(deviceId, startTime, endTime);
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LocationInfo Insert(@RequestBody LocationInfo locationInfo)
    {
        m_logger.info(">>>收到更新设备位置信息的请求:" + locationInfo.toString());
        return m_locationInfoService.Insert(locationInfo);
    }

    @RequestMapping(value = "/InsertList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int InsertList(@RequestBody List<LocationInfo> locationInfoList)
    {
        m_logger.info(">>>收到更新设备位置信息的请求:" + locationInfoList.size() + "条数据");
        return m_locationInfoService.InsertList(locationInfoList);
    }
}
