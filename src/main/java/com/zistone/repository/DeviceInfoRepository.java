package com.zistone.repository;

import com.zistone.bean.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer>
{

    @Query("select device from DeviceInfo device where device.m_name = :name")
    DeviceInfo FindDeviceByName(@Param("name") String name);

    @Query("select device from DeviceInfo device where device.m_id = :id")
    DeviceInfo FindDeviceById(@Param("id") int id);

    @Query("select device from DeviceInfo device where device.m_name = :name and device.m_id = :id")
    DeviceInfo FindDeviceByNameAndId(@Param("name") String name, @Param("id") int id);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.m_state = 1,device.m_lat = :lat,device.m_lot = :lot,device.m_height = :height where " +
            "device.m_name = " + ":name")
    int UpdateDeviceByName(@Param("name") String name, @Param("lat") double lat, @Param("lot") double lot, @Param("height") double height);

    @Transactional
    //清除底层持久化上下文
    @Modifying(clearAutomatically = true)
    @Query("update DeviceInfo device set device.m_state = 1,device.m_lat = :lat,device.m_lot = :lot,device.m_height = :height where " +
            "device.m_id = " + ":id")
    int UpdateDeviceById(@Param("id") int id, @Param("lat") double lat, @Param("lot") double lot,
                         @Param("height") double height);
}
