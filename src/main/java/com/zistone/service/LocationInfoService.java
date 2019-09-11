package com.zistone.service;

import com.zistone.bean.LocationInfo;
import com.zistone.repository.LocationInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocationInfoService
{
    private Logger m_logger = LoggerFactory.getLogger(LocationInfoService.class);

    @Resource
    private LocationInfoRepository m_locationInfoRepository;

    public List<LocationInfo> FindByDeviceId(String deviceId)
    {
        List<LocationInfo> list = m_locationInfoRepository.FindByDeviceId(deviceId);
        return list;
    }

    public List<LocationInfo> FindByDeviceIdAndTime(String deviceId, long startTime, long endTime)
    {
        List<LocationInfo> list = m_locationInfoRepository.FindByDeviceIdAndTime(deviceId, startTime, endTime);
        return list;
    }

    @Transactional
    public LocationInfo Insert(LocationInfo locationInfo)
    {
        return m_locationInfoRepository.insert(locationInfo);
    }

    @Transactional
    public int InsertList(List<LocationInfo> locationInfoList)
    {
        return m_locationInfoRepository.insert(locationInfoList).size();
    }

}
