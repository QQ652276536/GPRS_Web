package com.zistone.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "locationinfo")
public class LocationInfo
{
    /**
     * 自增主键(由数据库生成)
     */
    @Id
    private String m_id;

    /**
     * 设备编号
     */
    private String m_deviceId;

    /**
     * 纬度
     */
    private double m_lat;

    /**
     * 经度
     */
    private double m_lot;

    /**
     * 定位时间(单位:秒)
     */
    private long m_time;

    @Override
    public String toString()
    {
        return "LocationInfo{" + "m_id='" + m_id + '\'' + ", m_deviceId='" + m_deviceId + '\'' + ", m_lat=" + m_lat + ", m_lot=" + m_lot + ", m_time=" + m_time + '}';
    }

    public String getM_id()
    {
        return m_id;
    }

    public void setM_id(String m_id)
    {
        this.m_id = m_id;
    }

    public String getM_deviceId()
    {
        return m_deviceId;
    }

    public void setM_deviceId(String m_deviceId)
    {
        this.m_deviceId = m_deviceId;
    }

    public double getM_lat()
    {
        return m_lat;
    }

    public void setM_lat(double m_lat)
    {
        this.m_lat = m_lat;
    }

    public double getM_lot()
    {
        return m_lot;
    }

    public void setM_lot(double m_lot)
    {
        this.m_lot = m_lot;
    }

    public long getM_time()
    {
        return m_time;
    }

    public void setM_time(long m_time)
    {
        this.m_time = m_time;
    }
}
