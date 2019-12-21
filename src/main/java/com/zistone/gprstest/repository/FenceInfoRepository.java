package com.zistone.gprstest.repository;

import com.zistone.gprstest.bean.FenceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FenceInfoRepository extends JpaRepository<FenceInfo, Integer>
{
    @Query("select fenceinfo from FenceInfo fenceinfo where fenceinfo.m_deviceId = :deviceId")
    List<FenceInfo> FindByDeviceId(@Param("deviceId") String deviceId);

}
