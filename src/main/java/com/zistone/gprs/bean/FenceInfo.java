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
        return "FenceInfo{" + "m_id=" + m_id + ", m_deviceId='" + m_deviceId + '\'' + ", m_name='" + m_name + '\'' + ", m_address='" + m_address + '\'' + ", m_setTime=" + SIMPLEDATEFORMAT
                .format(m_setTime) + ", m_radius=" + m_radius + ", m_lat=" + m_lat + ", m_lot=" + m_lot + '}';
    }

    /**
     * 自增主键(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;

    /**
     * 设备编号
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备编号'")
    private String m_deviceId;

    /**
     * 围栏名称
     */
    @Column(columnDefinition = "varchar(50) default '' comment '围栏名称'")
    private String m_name;

    /**
     * 地址
     */
    @Column(columnDefinition = "varchar(50) default '' comment '地址'")
    private String m_address;

    /**
     * 设置时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '设置时间'")
    private Date m_setTime;

    /**
     * 半径
     */
    @Column(columnDefinition = "int default 1000 comment '半径'")
    private double m_radius;

    /**
     * 纬度
     */
    @Column(columnDefinition = "double default '0' comment '纬度'")
    private double m_lat;

    /**
     * 经度
     */
    @Column(columnDefinition = "double default '0' comment '经度'")
    private double m_lot;

    public int getM_id()
    {
        return m_id;
    }

    public void setM_id(int m_id)
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

    public String getM_name()
    {
        return m_name;
    }

    public void setM_name(String m_name)
    {
        this.m_name = m_name;
    }

    public String getM_address()
    {
        return m_address;
    }

    public void setM_address(String m_address)
    {
        this.m_address = m_address;
    }

    public Date getM_setTime()
    {
        return m_setTime;
    }

    public void setM_setTime(Date m_setTime)
    {
        this.m_setTime = m_setTime;
    }

    public double getM_radius()
    {
        return m_radius;
    }

    public void setM_radius(double m_radius)
    {
        this.m_radius = m_radius;
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
}
