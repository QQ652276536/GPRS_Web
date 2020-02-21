package com.zistone.gprs.repository;

import com.zistone.gprs.bean.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaterialRepository extends JpaRepository<Material, Integer>
{
    @Query("select material from Material material where material.deviceAddress = :deviceAddress")
    Material FindByDeviceAddress(@Param("deviceAddress") String deviceAddress);
}
