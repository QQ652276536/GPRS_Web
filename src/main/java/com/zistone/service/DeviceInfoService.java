package com.zistone.service;

import com.zistone.bean.DeviceInfo;
import com.zistone.repository.DeviceInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class DeviceInfoService
{

    @Resource
    private DeviceInfoRepository m_deviceInfoRepository;

    /**
     * 查找鉴权码
     *
     * @param akCode
     * @return
     */
    public String FindAKCode(String akCode)
    {
        if (null != akCode && !"".equals(akCode))
        {
            List<DeviceInfo> list = FindAllDevice();
            for (DeviceInfo tempDeviceInfo : list)
            {
                if (akCode.equals(tempDeviceInfo.getM_akCode()))
                {
                    return "1";
                }
            }
        }
        return "0";
    }

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
    public DeviceInfo InsertDevice(DeviceInfo deviceInfo)
    {
        DeviceInfo existDevice = m_deviceInfoRepository.FindDeviceByName(deviceInfo.getM_name());
        //暂时关闭名称验证
        existDevice = null;
        if (existDevice == null)
        {
            //鉴权码
            String akCode = "";
            Random random = new Random();
            for (int i = 0; i < 8; i++)
            {
                int num = random.nextInt(3) % 4;
                switch (num)
                {
                    //随机数字
                    case 0:
                        akCode += random.nextInt(10);
                        break;
                    //随机大写字母
                    case 1:
                        akCode += (char) (random.nextInt(26) + 65);
                        break;
                    //随机小写字母
                    case 2:
                        akCode += (char) (random.nextInt(26) + 97);
                        break;
                    default:
                        break;
                }
            }
            existDevice.setM_akCode(akCode);
            return m_deviceInfoRepository.save(deviceInfo);
        }
        else
        {
            return null;
        }
    }

    /**
     * 更新设备
     *
     * @param deviceInfo
     * @return
     */
    public int UpdateDeviceByName(DeviceInfo deviceInfo)
    {
        return m_deviceInfoRepository
                .UpdateDeviceByName(deviceInfo.getM_name(), deviceInfo.getM_lat(), deviceInfo.getM_lot(), deviceInfo.getM_height());
    }

    /**
     * 更新设备
     *
     * @param deviceInfo
     * @return
     */
    public int UpdateDeviceById(DeviceInfo deviceInfo)
    {
        return m_deviceInfoRepository
                .UpdateDeviceById(deviceInfo.getM_id(), deviceInfo.getM_lat(), deviceInfo.getM_lot(),
                        deviceInfo.getM_height());
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
