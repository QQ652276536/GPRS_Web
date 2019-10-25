package com.zistone.bean;

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
        return "DeviceInfo{" + "m_id=" + m_id + ", m_deviceId='" + m_deviceId + '\'' + ", m_sim=" + m_sim + ", m_name='" + m_name + '\'' + ", m_type='" + m_type + '\'' + ", m_state=" + m_state + ", m_lat=" + m_lat + ", m_lot=" + m_lot + ", m_height=" + m_height + ", m_createTime=" + SIMPLEDATEFORMAT
                .format(m_createTime) + ", m_updateTime=" + SIMPLEDATEFORMAT
                .format(m_updateTime) + ", m_comment='" + m_comment + '\'' + ", m_akCode='" + m_akCode + '\'' + ", m_temperature=" + m_temperature + ", m_electricity=" + m_electricity + '}';
    }

    /**
     * 自增主键(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;

    /**
     * 设备编号,设备自带
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备编号'")
    private String m_deviceId;

    /**
     * SIM卡号
     */
    @Column(columnDefinition = "varchar(50) default '' comment 'SIM卡号'")
    private String m_sim;

    /**
     * 设备名
     */
    @Column(columnDefinition = "varchar(50) default '' comment '设备名'")
    private String m_name;

    /**
     * 设备类型
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备类型'")
    public String m_type;

    /**
     * 设备状态
     */
    @Column(nullable = false, columnDefinition = "int default '1' comment '设备状态:0离线1在线'")
    private int m_state;

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

    /**
     * 海拔
     */
    @Column(columnDefinition = "int default 0 comment '高度'")
    private int m_height;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '创建时间'")
    private Date m_createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '修改时间'")
    private Date m_updateTime;

    /**
     * 备注
     */
    @Column(columnDefinition = "varchar(200) default '' comment '备注'")
    private String m_comment;

    /**
     * 鉴权码
     */
    @Column(columnDefinition = "varchar(15) default '' comment '鉴权码'")
    private String m_akCode;

    /**
     * 温度
     */
    @Column(columnDefinition = "int default '0' comment '温度'")
    private int m_temperature;

    /**
     * 剩余电量
     */
    @Column(columnDefinition = "int default '0' comment '剩余电量'")
    private int m_electricity;

    public int getM_id()
    {
        return m_id;
    }

    public void setM_id(int m_id)
    {
        this.m_id = m_id;
    }

    public String getM_name()
    {
        return m_name;
    }

    public void setM_name(String m_name)
    {
        this.m_name = m_name;
    }

    public String getM_type()
    {
        return m_type;
    }

    public void setM_type(String m_type)
    {
        this.m_type = m_type;
    }

    public int getM_state()
    {
        return m_state;
    }

    public void setM_state(int m_state)
    {
        this.m_state = m_state;
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

    public int getM_height()
    {
        return m_height;
    }

    public void setM_height(int m_height)
    {
        this.m_height = m_height;
    }

    public Date getM_createTime()
    {
        return m_createTime;
    }

    public void setM_createTime(Date m_createTime)
    {
        this.m_createTime = m_createTime;
    }

    public Date getM_updateTime()
    {
        return m_updateTime;
    }

    public void setM_updateTime(Date m_updateTime)
    {
        this.m_updateTime = m_updateTime;
    }

    public String getM_akCode()
    {
        return m_akCode;
    }

    public void setM_akCode(String m_akCode)
    {
        this.m_akCode = m_akCode;
    }

    public String getM_deviceId()
    {
        return m_deviceId;
    }

    public void setM_deviceId(String m_deviceId)
    {
        this.m_deviceId = m_deviceId;
    }

    public String getM_sim()
    {
        return m_sim;
    }

    public void setM_sim(String m_sim)
    {
        this.m_sim = m_sim;
    }

    public String getM_comment()
    {
        return m_comment;
    }

    public void setM_comment(String m_comment)
    {
        this.m_comment = m_comment;
    }

    public int getM_temperature()
    {
        return m_temperature;
    }

    public void setM_temperature(int m_temperature)
    {
        this.m_temperature = m_temperature;
    }

    public int getM_electricity()
    {
        return m_electricity;
    }

    public void setM_electricity(int m_electricity)
    {
        this.m_electricity = m_electricity;
    }
}
