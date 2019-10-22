package com.zistone.repository;

import com.zistone.bean.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LocationInfoRepository extends JpaRepository<LocationInfo, Integer>
{
    @Query("select location from LocationInfo location where location.m_deviceId = :deviceId")
    List<LocationInfo> FindByDeviceId(@Param("deviceId") String deviceId);

    @Query("select location from LocationInfo location where location.m_deviceId = :deviceId and location.m_createTime between " +
            ":startDate" + " and " + ":endDate order by location.m_createTime desc")
    List<LocationInfo> FindByDeviceIdAndTime(
            @Param("deviceId") String deviceId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
