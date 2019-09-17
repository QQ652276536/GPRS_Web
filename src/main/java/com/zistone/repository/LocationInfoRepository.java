package com.zistone.repository;

import com.zistone.bean.LocationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LocationInfoRepository extends MongoRepository<LocationInfo, String>
{
    @Query(value = "{'m_deviceId':?0}")
    List<LocationInfo> FindByDeviceId(String deviceId);

    @Query(value = "{m_deviceId:?0,m_time:{$gt:?1,$lt:?2}}")
    List<LocationInfo> FindByDeviceIdAndTime(String deviceId, long startTime, long endTime);
}