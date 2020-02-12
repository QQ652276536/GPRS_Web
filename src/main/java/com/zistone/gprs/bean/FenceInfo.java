package com.zistone.gprs.bean;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
//监听实体变化
@EntityListeners(AuditingEntityListener.class)
@Table(name = "fenceinfo")
public class FenceInfo
{
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String toString()
    {
        return "FenceInfo{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", setTime=" + SIMPLEDATEFORMAT.format(setTime) +
                ", radius=" + radius +
                ", lat=" + lat +
                ", lot=" + lot +
                '}';
    }

    /**
     * 自增主键(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 设备编号
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备编号'")
    private String deviceId;

    /**
     * 围栏名称
     */
    @Column(columnDefinition = "varchar(50) default '' comment '围栏名称'")
    private String name;

    /**
     * 地址
     */
    @Column(columnDefinition = "varchar(50) default '' comment '地址'")
    private String address;

    /**
     * 设置时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '设置时间'")
    private Date setTime;

    /**
     * 半径
     */
    @Column(columnDefinition = "int default 1000 comment '半径'")
    private double radius;

    /**
     * 纬度
     */
    @Column(columnDefinition = "double default '0' comment '纬度'")
    private double lat;

    /**
     * 经度
     */
    @Column(columnDefinition = "double default '0' comment '经度'")
    private double lot;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Date getSetTime()
    {
        return setTime;
    }

    public void setSetTime(Date setTime)
    {
        this.setTime = setTime;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public double getLot()
    {
        return lot;
    }

    public void setLot(double lot)
    {
        this.lot = lot;
    }
}
