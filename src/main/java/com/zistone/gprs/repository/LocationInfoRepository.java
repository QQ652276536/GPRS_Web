package com.zistone.gprs.repository;

import com.zistone.gprs.bean.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LocationInfoRepository extends JpaRepository<LocationInfo, Integer>
{
    @Query("select location from LocationInfo location where location.deviceId = :deviceId")
    List<LocationInfo> FindByDeviceId(@Param("deviceId") String deviceId);

    @Query("select location from LocationInfo location where location.deviceId = :deviceId and location.lat > 0 and location.lot > "
            + "0 and location.createTime between :startDate and :endDate order by location.createTime asc")
    List<LocationInfo> FindByDeviceIdAndTime(
            @Param("deviceId") String deviceId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(nativeQuery = true, value = "SELECT * FROM locationinfo where deviceid = ? and DATESUB(CURDATE(), INTERVAL ? DAY) <=" +
            " date(createtime) and lat > 0 and lot > 0 GROUP BY DATE(createtime) ORDER BY createtime DESC")
    List<LocationInfo> FindDescDaysLastDataByDeviceId(String deviceId, int day);
}
