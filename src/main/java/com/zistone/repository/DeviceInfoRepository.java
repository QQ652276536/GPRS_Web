package com.zistone.repository;

import com.zistone.bean.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceInfoRepository extends JpaRepository<DeviceInfo, Integer> {

    @Query("select device from DeviceInfo device where device.m_deviceName = :name")
    DeviceInfo FindDeviceByName(@Param("name") String name);

    @Query("select device from DeviceInfo device where device.m_id = :id")
    DeviceInfo FindDeviceById(@Param("id") int id);

    @Query("select device from DeviceInfo device where device.m_deviceName = :name and device.m_id = :id")
    DeviceInfo FindDeviceByNameAndId(@Param("name") String name, @Param("id") int id);
}
