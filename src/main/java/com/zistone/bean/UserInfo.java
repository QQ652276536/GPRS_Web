package com.zistone.bean;

import com.zistone.validator.PhoneNumber;
import com.zistone.validator.RealName;
import com.zistone.validator.UserName;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
//监听实体类增删操作
@EntityListeners(AuditingEntityListener.class)
@Table(name = "userinfo")
public class UserInfo
{
    public UserInfo()
    {
    }

    @Override
    public String toString()
    {
        return "UserInfo{" + "m_id=" + m_id + ", m_userName='" + m_userName + '\'' + ", m_realName='" + m_realName + '\'' + ", " +
                "m_phoneNumber='" + m_phoneNumber + '\'' + ", m_level=" + m_level + ", m_state=" + m_state + ", m_craeteTime=" + m_craeteTime + ", m_updateTime=" + m_updateTime + ", m_password='" + m_password + '\'' + '}';
    }

    /**
     * 用户编号(由数据库生成)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_id;

    public int getM_id()
    {
        return m_id;
    }

    public void setM_id(int m_id)
    {
        this.m_id = m_id;
    }

    /**
     * 用户头像
     * 大对象类型,使用迟延加载
     */
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "blob(256) comment '用户头像'")
    private byte[] m_image;

    public byte[] getM_image()
    {
        return m_image;
    }

    public void setM_image(byte[] m_image)
    {
        this.m_image = m_image;
    }

    /**
     * 用户名
     */
    @UserName
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '用户名'")
    private String m_userName;

    public String getM_userName()
    {
        return m_userName;
    }

    public void setM_userName(String m_userName)
    {
        this.m_userName = m_userName;
    }

    /**
     * 用户实名
     */
    @RealName
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '用户实名'")
    private String m_realName;

    public String getM_realName()
    {
        return m_realName;
    }

    public void setM_realName(String m_realName)
    {
        this.m_realName = m_realName;
    }

    /**
     * 注册手机号
     */
    @PhoneNumber
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '注册手机号'")
    private String m_phoneNumber;

    public String getM_phoneNumber()
    {
        return m_phoneNumber;
    }

    public void setM_phoneNumber(String m_phoneNumber)
    {
        this.m_phoneNumber = m_phoneNumber;
    }

    /**
     * 用户权限
     */
    @Column(nullable = false, columnDefinition = "int default '1' comment '用户权限:1普通用户2管理员3超级管理员'")
    private int m_level;

    public int getM_level()
    {
        return m_level;
    }

    public void setM_level(int m_level)
    {
        this.m_level = m_level;
    }

    /**
     * 用户状态
     */
    @Column(columnDefinition = "int default '1' comment '用户状态:1正常使用2注销3冻结'")
    private int m_state;

    public int getM_state()
    {
        return m_state;
    }

    public void setM_state(int m_state)
    {
        this.m_state = m_state;
    }

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '创建时间'")
    private Date m_craeteTime;

    public Date getM_craeteTime()
    {
        return m_craeteTime;
    }

    public void setM_craeteTime(Date m_craeteTime)
    {
        this.m_craeteTime = m_craeteTime;
    }

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(columnDefinition = "datetime default CURRENT_TIMESTAMP comment '修改时间'")
    private Date m_updateTime;

    public Date getM_updateTime()
    {
        return m_updateTime;
    }

    public void setM_updateTime(Date m_updateTime)
    {
        this.m_updateTime = m_updateTime;
    }

    /**
     * 密码
     */
    //TODO:应该使用加密
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '登录密码'")
    private String m_password;

    public String getM_password()
    {
        return m_password;
    }

    public void setM_password(String m_password)
    {
        this.m_password = m_password;
    }
}
