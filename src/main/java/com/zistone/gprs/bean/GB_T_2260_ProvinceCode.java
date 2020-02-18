package com.zistone.gprs.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 《中华人民共和国行政区划代码》的省级代码
 */
@Entity
//监听实体类增删操作
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gb_t_2260_provincecode")
public class GB_T_2260_ProvinceCode
{
    @Override
    public String toString()
    {
        return "GBT2260ProvinceCode{" + "id=" + id + ", code=" + code + ", name='" + name + '\'' + '}';
    }

    /**
     * 省会编号(手动生成)
     */
    @Id
    private int id;

    /**
     * 省会编码
     */
    @Column(nullable = false, columnDefinition = "int default '0' comment '省会编码'")
    private int code;

    /**
     * 省会名称
     *
     * @return
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '省会名称'")
    private String name;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
