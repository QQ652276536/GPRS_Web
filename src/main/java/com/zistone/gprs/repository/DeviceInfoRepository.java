package com.zistone.gprs.repository;

import com.zistone.gprs.bean.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer>
{
    @Query("select device from DeviceInfo device where device.m_deviceId = :deviceId")
    DeviceInfo FindByDeviceId(@Param("deviceId") String deviceId);

    @Query("select device from DeviceInfo device where device.m_akCode = :akCode")
    DeviceInfo FindByAKCode(@Param("akCode") String akCode);

    @Query("select device from DeviceInfo device where device.m_id = :id")
    DeviceInfo FindById(@Param("id") int id);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.m_state = 1,device.m_lat = :lat,device.m_lot = :lot,device.m_height = :height,device" +
            ".m_updateTime = CURRENT_TIMESTAMP" + " where " + "device.m_deviceId = " + ":deviceId")
    int UpdateLocationByDeviceId(
            @Param("deviceId") String deviceId, @Param("lat") double lat, @Param("lot") double lot, @Param("height") int height);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.m_state = 1,device.m_lat = :lat,device.m_lot = :lot,device.m_height = :height,device" +
            ".m_temperature = :temperature,device.m_electricity = :electricity,device.m_updateTime = CURRENT_TIMESTAMP" + " where device" + ".m_deviceId = :deviceId")
    int UpdateByDeviceId(
            @Param("deviceId") String deviceId,
            @Param("lat") double lat,
            @Param("lot") double lot,
            @Param("height") int height, @Param("temperature") int temperature, @Param("electricity") int electricity);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.m_updateTime = CURRENT_TIMESTAMP where device.m_deviceId = " + ":#{#deviceInfo.m_deviceId}")
    int UpdateAKCodeByDeviceId(@Param("deviceInfo") DeviceInfo deviceInfo);
}