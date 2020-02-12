package com.zistone.gprs.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 《中华人民共和国行政区划代码》的区域级代码
 */
@Entity
//监听实体类增删操作
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gbt2260areacode")
public class GB_T_2260_AreaCode
{
    @Override
    public String toString()
    {
        return "GBT2260AreaCode{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", parentCityId=" + parentCityId +
                '}';
    }

    /**
     * 区域编号(手动生成)
     */
    @Id
    private int id;

    /**
     * 区域编码
     */
    @Column(nullable = false, columnDefinition = "int default '0' comment '区域编码'")
    private int code;

    /**
     * 区域名称
     *
     * @return
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '区域名称'")
    private String name;

    /**
     * 所属市
     */
    @Column(nullable = false, columnDefinition = "int default '0' comment '所属市'")
    private int parentCityId;

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

    public int getParentCityId()
    {
        return parentCityId;
    }

    public void setParentCityId(int parentCityId)
    {
        this.parentCityId = parentCityId;
    }
}
