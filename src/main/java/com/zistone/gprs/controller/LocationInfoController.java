package com.zistone.gprs.controller;

import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.service.LocationInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.TimeZone.getTimeZone;

//@RestController = @Controller + @ReponseBody
@RestController
@RequestMapping("/LocationInfo")
public class LocationInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationInfoController.class);
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    //@Resource默认按byName自动注入
    //@Autowired默认按byType自动注入
    @Resource
    LocationInfoService _locationInfoService;

    @RequestMapping(value = "/FindAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<LocationInfo> FindAll() {
        LOGGER.info("收到查询所有设备的历史位置的请求:");
        List<LocationInfo> result = _locationInfoService.FindAll();
        result.forEach(v -> {
            LOGGER.info(v.toString());
        });
        return result;
    }

    @RequestMapping(value = "/FindByDeviceId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceId(@RequestParam("deviceId") String deviceId) {
        LOGGER.info(String.format("收到查询设备%s所有时间的历史轨迹请求:", deviceId));
        return _locationInfoService.FindByDeviceId(deviceId);
    }

    @RequestMapping(value = "/FindByDeviceIdAndBetweenTime", method = RequestMethod.POST, produces = "application" +
            "/json;charset=UTF-8")
    public List<LocationInfo> FindByDeviceIdAndBetweenTime(
            @RequestParam("deviceId") String deviceId, @RequestParam("startTime") long startTime, @RequestParam(
            "endTime") long endTime) {
        //不设置会比Android端多八个小时
        SIMPLEDATEFORMAT.setTimeZone(getTimeZone("GMT+0"));
        Date date1 = new Date(startTime);
        Date date2 = new Date(endTime);
        String startStr = SIMPLEDATEFORMAT.format(date1);
        String endStr = SIMPLEDATEFORMAT.format(date2);
        LOGGER.info(String.format("收到查询设备%s从%s至%s的历史轨迹请求:", deviceId, startStr, endStr));
        return _locationInfoService.FindByDeviceIdAndTime(deviceId, date1, date2);
    }

    @RequestMapping(value = "/Insert", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public LocationInfo Insert(@RequestBody LocationInfo locationInfo) {
        LOGGER.info(String.format("收到新增设备%s历史位置的请求:", locationInfo.getDeviceId()));
        return _locationInfoService.Insert(locationInfo);
    }

    @RequestMapping(value = "/InsertList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public int InsertList(@RequestBody List<LocationInfo> locationInfoList) {
        LOGGER.info(String.format("收到批量更新位置的请求:%s条数据", locationInfoList.size()));
        return _locationInfoService.InsertList(locationInfoList);
    }

    @RequestMapping(value = "/FindDescDaysLastDataByDeviceId", method = RequestMethod.POST, produces =
            "application" + "/json;charset=UTF-8")
    public List<LocationInfo> FindDescDaysLastDataByDeviceId(@RequestParam("deviceId") String deviceId,
                                                             @RequestParam("days") int days) {
        LOGGER.info(String.format("收到查询设备%s最近%s天最后位置的请求:", deviceId, days));
        return _locationInfoService.FindDescDaysLastDataByDeviceId(deviceId, days - 1);
    }

}
