package com.zistone.controller;

import com.zistone.bean.LocationInfo;
import org.junit.Test;

import java.util.List;

public class LocationInfoControllerTest extends LocationInfoController
{

    @Test
    public void findByDeviceId()
    {
        List<LocationInfo> list = m_locationInfoService.FindByDeviceId("551030006334");

        for(LocationInfo tempLocationInfo : list)
        {}
    }

    @Test
    public void findByDeviceIdAndBetweenTime()
    {
    }

    @Test
    public void insert()
    {
    }

    @Test
    public void insertList()
    {
    }
}
