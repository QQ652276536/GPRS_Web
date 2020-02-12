package com.zistone.gprs.service;

import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.repository.LocationInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class LocationInfoService
{
    private Logger _logger = LoggerFactory.getLogger(LocationInfoService.class);

    @Resource
    private LocationInfoRepository _locationInfoRepository;

    public List<LocationInfo> FindByDeviceId(String deviceId)
    {
        List<LocationInfo> list = _locationInfoRepository.FindByDeviceId(deviceId);
        return list;
    }

    public List<LocationInfo> FindByDeviceIdAndTime(String deviceId, Date startDate, Date endDate)
    {
        List<LocationInfo> list = _locationInfoRepository.FindByDeviceIdAndTime(deviceId, startDate, endDate);
        return list;
    }

    public List<LocationInfo> FindDescDaysLastDataByDeviceId(String deviceId, int days)
    {
        return _locationInfoRepository.FindDescDaysLastDataByDeviceId(deviceId, days);
    }

    @Transactional
    public LocationInfo Insert(LocationInfo locationInfo)
    {
        return _locationInfoRepository.save(locationInfo);
    }

    @Transactional
    public int InsertList(List<LocationInfo> locationInfoList)
    {
        return _locationInfoRepository.saveAll(locationInfoList).size();
    }

}
