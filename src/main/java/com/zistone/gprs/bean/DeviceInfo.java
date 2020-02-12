package com.zistone.gprs.bean;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
//监听实体变化
@EntityListeners(AuditingEntityListener.class)
@Table(name = "deviceinfo")
public class DeviceInfo
{
    private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String toString()
    {
        return "DeviceInfo{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", sim='" + sim + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", state=" + state +
                ", lat=" + lat +
                ", lot=" + lot +
                ", height=" + height +
                ", createTime=" + SIMPLEDATEFORMAT.format(createTime) +
                ", updateTime=" + SIMPLEDATEFORMAT.format(updateTime) +
                ", comment='" + comment + '\'' +
                ", akCode='" + akCode + '\'' +
                ", temperature=" + temperature +
                ", electricity=" + electricity +
                '}';
    }

    /**
     * 自增主键(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 设备编号,设备自带
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备编号'")
    private String deviceId;

    /**
     * SIM卡号
     */
    @Column(columnDefinition = "varchar(50) default '' comment 'SIM卡号'")
    private String sim;

    /**
     * 设备名
     */
    @Column(columnDefinition = "varchar(50) default '' comment '设备名'")
    private String name;

    /**
     * 设备类型
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备类型'")
    public String type;

    /**
     * 设备状态
     */
    @Column(nullable = false, columnDefinition = "int default '1' comment '设备状态:0离线1在线'")
    private int state;

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

    /**
     * 海拔
     */
    @Column(columnDefinition = "int default 0 comment '高度'")
    private int height;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '修改时间'")
    private Date updateTime;

    /**
     * 备注
     */
    @Column(columnDefinition = "varchar(200) default '' comment '备注'")
    private String comment;

    /**
     * 鉴权码
     */
    @Column(columnDefinition = "varchar(15) default '' comment '鉴权码'")
    private String akCode;

    /**
     * 温度
     */
    @Column(columnDefinition = "int default '0' comment '温度'")
    private int temperature;

    /**
     * 剩余电量
     */
    @Column(columnDefinition = "int default '0' comment '剩余电量'")
    private int electricity;

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

    public String getSim()
    {
        return sim;
    }

    public void setSim(String sim)
    {
        this.sim = sim;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
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

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getAkCode()
    {
        return akCode;
    }

    public void setAkCode(String akCode)
    {
        this.akCode = akCode;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }

    public int getElectricity()
    {
        return electricity;
    }

    public void setElectricity(int electricity)
    {
        this.electricity = electricity;
    }
}
