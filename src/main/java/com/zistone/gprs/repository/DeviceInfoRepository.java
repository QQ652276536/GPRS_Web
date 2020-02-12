package com.zistone.gprs.repository;

import com.zistone.gprs.bean.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer>
{
    @Query("select device from DeviceInfo device where device.deviceId = :deviceId")
    DeviceInfo FindByDeviceId(@Param("deviceId") String deviceId);

    @Query("select device from DeviceInfo device where device.akCode = :akCode")
    DeviceInfo FindByAKCode(@Param("akCode") String akCode);

    @Query("select device from DeviceInfo device where device.id = :id")
    DeviceInfo FindById(@Param("id") int id);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.state = 1,device.lat = :lat,device.lot = :lot,device.height = :height,device" +
            ".updateTime = CURRENT_TIMESTAMP" + " where " + "device.deviceId = " + ":deviceId")
    int UpdateLocationByDeviceId(
            @Param("deviceId") String deviceId, @Param("lat") double lat, @Param("lot") double lot, @Param("height") int height);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.state = 1,device.lat = :lat,device.lot = :lot,device.height = :height,device" +
            ".temperature = :temperature,device.electricity = :electricity,device.updateTime = CURRENT_TIMESTAMP" + " where device" + ".deviceId = :deviceId")
    int UpdateByDeviceId(
            @Param("deviceId") String deviceId,
            @Param("lat") double lat,
            @Param("lot") double lot,
            @Param("height") int height, @Param("temperature") int temperature, @Param("electricity") int electricity);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.updateTime = CURRENT_TIMESTAMP where device.deviceId = " + ":#{#deviceInfo.deviceId}")
    int UpdateAKCodeByDeviceId(@Param("deviceInfo") DeviceInfo deviceInfo);
}
