package com.zistone.service;

import com.zistone.bean.DeviceInfo;
import com.zistone.repository.DeviceInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeviceInfoService
{

    @Resource
    private DeviceInfoRepository m_deviceInfoRepository;

    @Transactional
    public void SaveList(List<DeviceInfo> deviceInfoList)
    {
        m_deviceInfoRepository.saveAll(deviceInfoList);
    }

    /**
     * 根据编号查找设备
     *
     * @param id
     * @return
     */
    public DeviceInfo FindDeviceById(int id)
    {
        return m_deviceInfoRepository.FindDeviceById(id);
    }

    /**
     * 根据名称和编号查找设备
     *
     * @param name
     * @param id
     * @return
     */
    public DeviceInfo FindDeviceByNameAndId(String name, int id)
    {
        return m_deviceInfoRepository.FindDeviceByNameAndId(name, id);
    }

    /**
     * 根据名称查找设备
     *
     * @param name
     * @return
     */
    public DeviceInfo FindDeviceByName(String name)
    {
        return m_deviceInfoRepository.FindDeviceByName(name);
    }

    /**
     * 查询所有的设备
     *
     * @return
     */
    public List<DeviceInfo> FindAllDevice()
    {
        return m_deviceInfoRepository.findAll();
    }

    /**
     * 新增一台设备,不允许设备名重复
     *
     * @param deviceInfo
     * @return
     */
    public String InsertDevice(DeviceInfo deviceInfo)
    {
        DeviceInfo existDevice = m_deviceInfoRepository.FindDeviceByName(deviceInfo.getM_deviceName());
        if (existDevice == null)
        {
            DeviceInfo saveDevice = m_deviceInfoRepository.save(deviceInfo);
            return saveDevice.getM_id() + "";
        }
        else if (deviceInfo.getM_deviceName().equals(existDevice.getM_deviceName()))
        {
            //设备添加失败,该设备名已存在
            return "-1";
        }
        else
        {
            //设备添加失败,未知错误
            return "-2";
        }
    }

    /**
     * 更新设备
     *
     * @param deviceInfo
     * @return
     */
    public DeviceInfo UpdateDevice(DeviceInfo deviceInfo)
    {
        DeviceInfo tempDeviceInfo = new DeviceInfo();
        tempDeviceInfo.setM_deviceName(deviceInfo.getM_deviceName());
        tempDeviceInfo.setM_type(deviceInfo.getM_type());
        tempDeviceInfo.setM_lat(deviceInfo.getM_lat());
        tempDeviceInfo.setM_lot(deviceInfo.getM_lot());
        tempDeviceInfo.setM_state(deviceInfo.getM_state());
        tempDeviceInfo.setM_description(deviceInfo.getM_description());
        return m_deviceInfoRepository.save(deviceInfo);
    }

    /**
     * 根据编号删除设备
     *
     * @param id
     */
    public void DelDeviceById(Integer id)
    {
        m_deviceInfoRepository.deleteById(id);
    }

    /**
     * 根据名称删除设备
     *
     * @param name
     * @return
     */
    public DeviceInfo DelDeviceByName(String name)
    {
        DeviceInfo tempDevice = m_deviceInfoRepository.FindDeviceByName(name);
        if (tempDevice != null)
        {
            m_deviceInfoRepository.delete(tempDevice);
        }
        return tempDevice;
    }

}
