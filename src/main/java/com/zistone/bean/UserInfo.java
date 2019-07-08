package com.zistone.bean;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" + "m_id=" + m_id + ", m_userName='" + m_userName + '\'' + ", m_realName='" + m_realName + '\'' + ", m_phoneNumber=" + m_phoneNumber + ", m_level=" + m_level + ", m_isDelete=" + m_isDelete + ", m_craeteTime=" + m_craeteTime + ", m_updateTime=" + m_updateTime + '}';
    }

    /**
     * 用户编号(由数据库生成)
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
     * 用户名
     */
    //TODO:使用自定义验证注解更好
    @NotEmpty(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '用户名'")
    private String m_userName;

    public String getM_userName() {
        return m_userName;
    }

    public void setM_userName(String m_userName) {
        this.m_userName = m_userName;
    }

    /**
     * 用户实名
     */
    //TODO:使用自定义验证注解更好
    @NotEmpty(message = "姓名不能为空")
    @NotBlank(message = "姓名不能为空")
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '用户实名'")
    private String m_realName;

    public String getM_realName() {
        return m_realName;
    }

    public void setM_realName(String m_realName) {
        this.m_realName = m_realName;
    }

    /**
     * 注册手机号
     */
    //TODO:使用自定义验证注解更好
    @Length(min = 11, max = 11, message = "手机号码非法")
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '注册手机号'")
    private int m_phoneNumber;

    public int getM_phoneNumber() {
        return m_phoneNumber;
    }

    public void setM_phoneNumber(int m_phoneNumber) {
        this.m_phoneNumber = m_phoneNumber;
    }

    /**
     * 用户权限
     */
    @Column(nullable = false, columnDefinition = "int default '1' comment '用户权限:1普通用户2管理员3超级管理员'")
    private int m_level;

    public int getM_level() {
        return m_level;
    }

    public void setM_level(int m_level) {
        this.m_level = m_level;
    }

    /**
     * 用户留痕
     */
    @Column(columnDefinition = "int default '1' comment '用户留痕:0已注销1正在使用'")
    private boolean m_isDelete;

    public boolean getM_isDelete() {
        return m_isDelete;
    }

    public void setM_isDelete(boolean m_isDelete) {
        this.m_isDelete = m_isDelete;
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
}
