package com.zistone.controller;

import com.zistone.bean.LocationInfo;
import com.zistone.service.LocationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static java.util.TimeZone.getTimeZone;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/LocationInfo")
public class LocationInfoController
{
    private Logger m_logger = LoggerFactory.getLogger(LocationInfoController.class);
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    LocationInfoService m_locationInfoService;

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId)
    {
        m_logger.info(">>>收到查询设备" + deviceId + "所有时间的历史轨迹请求");
        return m_locationInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/FindByDeviceIdAndBetweenTime", method = RequestMethod.POST, produces = "application" +
            "/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceIdAndBetweenTime(
            @RequestParam("deviceId") String deviceId, @RequestParam("startTime") long startTime, @RequestParam(
            "endTime") long endTime)
    {
        //不设置会比Android端多八个小时
        SIMPLEDATEFORMAT.setTimeZone(getTimeZone("GMT+0"));
        String startStr = SIMPLEDATEFORMAT.format(new Date(startTime));
        String endStr = SIMPLEDATEFORMAT.format(new Date(endTime));
        m_logger.info(">>>收到查询设备" + deviceId + "从" + startStr + "至" + endStr + "的历史轨迹请求");
        return m_locationInfoService.FindByDeviceIdAndTime(deviceId, startTime, endTime);
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LocationInfo Insert(@RequestBody LocationInfo locationInfo)
    {
        m_logger.info(">>>收到更新设备" + locationInfo.getM_deviceId() + "位置信息的请求");
        return m_locationInfoService.Insert(locationInfo);
    }

    @RequestMapping(value = "/InsertList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int InsertList(@RequestBody List<LocationInfo> locationInfoList)
    {
        m_logger.info(">>>收到更新设备位置信息的请求:" + locationInfoList.size() + "条数据");
        return m_locationInfoService.InsertList(locationInfoList);
    }
}
