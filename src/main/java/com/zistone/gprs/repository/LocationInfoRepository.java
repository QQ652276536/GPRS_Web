package com.zistone.gprs.repository;

import com.zistone.gprs.bean.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LocationInfoRepository extends JpaRepository<LocationInfo, Integer>
{
    @Query("select location from LocationInfo location where location.m_deviceId = :deviceId")
    List<LocationInfo> FindByDeviceId(@Param("deviceId") String deviceId);

    @Query("select location from LocationInfo location where location.m_deviceId = :deviceId and location.m_lat > 0 and location.m_lot > "
            + "0 and location.m_createTime between :startDate and :endDate order by location.m_createTime asc")
    List<LocationInfo> FindByDeviceIdAndTime(
            @Param("deviceId") String deviceId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "SELECT * FROM locationinfo where m_device_id = ? and DATE_SUB(CURDATE(), INTERVAL ? DAY) <=" +
            " date(m_create_time) and m_lat > 0 and m_lot > 0 GROUP BY DATE(m_create_time) ORDER BY m_create_time DESC")
    List<LocationInfo> FindDescDaysLastDataByDeviceId(String deviceId, int day);

}