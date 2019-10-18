package com.zistone.service;

import com.zistone.bean.LocationInfo;
import com.zistone.repository.DeviceInfoRepository;
import com.zistone.repository.LocationInfoRepository;
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
    private Logger m_logger = LoggerFactory.getLogger(LocationInfoService.class);

    @Resource
    private LocationInfoRepository m_locationInfoRepository;

    @Resource
    private DeviceInfoRepository m_deviceInfoRepository;

    public List<LocationInfo> FindByDeviceId(String deviceId)
    {
        List<LocationInfo> list = m_locationInfoRepository.FindByDeviceId(deviceId);
        return list;
    }

    public List<LocationInfo> FindByDeviceIdAndTime(String deviceId, Date startDate, Date endDate)
    {
        List<LocationInfo> list = m_locationInfoRepository.FindByDeviceIdAndTime(deviceId, startDate, endDate);
        return list;
    }

    @Transactional
    public LocationInfo Insert(LocationInfo locationInfo)
    {
        //新增位置记录的同时更新设备表当前的位置信息
        m_deviceInfoRepository.UpdateByDeviceId(locationInfo.getM_deviceId(), locationInfo.getM_lat(), locationInfo.getM_lot(), 0.0);
        return m_locationInfoRepository.save(locationInfo);
    }

    @Transactional
    public int InsertList(List<LocationInfo> locationInfoList)
    {
        return m_locationInfoRepository.saveAll(locationInfoList).size();
    }

}
