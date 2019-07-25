package com.zistone.bean;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
//监听实体类增删操作
@EntityListeners(AuditingEntityListener.class)
@Table(name = "deviceinfo")
public class DeviceInfo {

    public DeviceInfo() {
    }

    /**
     * 设备编号(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    /**
     * 设备名
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备名'")
    private String m_deviceName;

    public String getM_deviceName() {
        return m_deviceName;
    }

    public void setM_deviceName(String m_deviceName) {
        this.m_deviceName = m_deviceName;
    }

    /**
     * 设备类型
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '设备类型'")
    public String m_type;

    public String getM_type() {
        return m_type;
    }

    public void setM_type(String m_type) {
        this.m_type = m_type;
    }

    /**
     * 设备状态
     */
    @Column(nullable = false, columnDefinition = "int default '1' comment '设备状态:0离线1在线'")
    private int m_state;

    public int getM_state() {
        return m_state;
    }

    public void setM_state(int m_state) {
        this.m_state = m_state;
    }

    /**
     * 纬度
     */
    @Column(columnDefinition = "double default '0' comment '纬度'")
    private double m_lat;

    public double getM_lat() {
        return m_lat;
    }

    public void setM_lat(double m_lat) {
        this.m_lat = m_lat;
    }

    /**
     * 经度
     */
    @Column(columnDefinition = "double default '0' comment '经度'")
    private double m_lot;

    public double getM_lot() {
        return m_lot;
    }

    public void setM_lot(double m_lot) {
        this.m_lot = m_lot;
    }

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '创建时间'")
    private Date m_craeteTime;

    public Date getM_craeteTime() {
        return m_craeteTime;
    }

    public void setM_craeteTime(Date m_craeteTime) {
        this.m_craeteTime = m_craeteTime;
    }

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '修改时间'")
    private Date m_updateTime;

    public Date getM_updateTime() {
        return m_updateTime;
    }

    public void setM_updateTime(Date m_updateTime) {
        this.m_updateTime = m_updateTime;
    }

    /**
     * 描述
     */
    @Column(columnDefinition = "varchar(200) default '' comment '描述'")
    private String m_description;

    public String getM_description() {
        return m_description;
    }

    public void setM_description(String m_description) {
        this.m_description = m_description;
    }
}
