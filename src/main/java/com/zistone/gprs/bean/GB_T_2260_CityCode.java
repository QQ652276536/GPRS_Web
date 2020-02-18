package com.zistone.gprs.bean;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 《中华人民共和国行政区划代码》的市级代码
 */
@Entity
//监听实体类增删操作
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gb_t_2260_citycode")
public class GB_T_2260_CityCode
{
    @Override
    public String toString()
    {
        return "GB_T_2260_CityCode{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", parentProvinceId=" + parentProvinceId +
                '}';
    }

    /**
     * 城市编号(手动生成)
     */
    @Id
    private int id;

    /**
     * 城市编码
     */
    @Column(nullable = false, columnDefinition = "int default '0' comment '城市编码'")
    private int code;

    /**
     * 城市名称
     *
     * @return
     */
    @Column(nullable = false, columnDefinition = "varchar(50) default '' comment '城市名称'")
    private String name;

    /**
     * 所属省
     */
    @Column(nullable = false, columnDefinition = "int default '0' comment '所属省'")
    private int parentProvinceId;

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

    public int getParentProvinceId()
    {
        return parentProvinceId;
    }

    public void setParentProvinceId(int parentProvinceId)
    {
        this.parentProvinceId = parentProvinceId;
    }

}
