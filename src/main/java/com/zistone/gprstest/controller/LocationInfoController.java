package com.zistone.gprstest.controller;

import com.zistone.gprstest.bean.LocationInfo;
import com.zistone.gprstest.service.LocationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.TimeZone.getTimeZone;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/LocationInfo")
public class LocationInfoController
{
    private Logger m_logger = LoggerFactory.getLogger(LocationInfoController.class);
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Autowired
    LocationInfoService m_locationInfoService;

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId)
    {
        m_logger.info(">>>收到查询" + deviceId + "所有时间的历史轨迹请求");
        return m_locationInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/FindByDeviceIdAndBetweenTime", method = RequestMethod.POST, produces = "application" + "/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceIdAndBetweenTime(
            @RequestParam("deviceId") String deviceId, @RequestParam("startTime") long startTime, @RequestParam("endTime") long endTime)
    {
        //不设置会比Android端多八个小时
        SIMPLEDATEFORMAT.setTimeZone(getTimeZone("GMT+0"));
        Date date1 = new Date(startTime);
        Date date2 = new Date(endTime);
        String startStr = SIMPLEDATEFORMAT.format(date1);
        String endStr = SIMPLEDATEFORMAT.format(date2);
        m_logger.info(">>>收到查询" + deviceId + "从" + startStr + "至" + endStr + "的历史轨迹请求");
        return m_locationInfoService.FindByDeviceIdAndTime(deviceId, date1, date2);
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LocationInfo Insert(@RequestBody LocationInfo locationInfo)
    {
        m_logger.info(">>>收到更新" + locationInfo.getM_deviceId() + "位置的请求");
        return m_locationInfoService.Insert(locationInfo);
    }

    @RequestMapping(value = "/InsertList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int InsertList(@RequestBody List<LocationInfo> locationInfoList)
    {
        m_logger.info(">>>收到更新位置的请求:" + locationInfoList.size() + "条数据");
        return m_locationInfoService.InsertList(locationInfoList);
    }
}
