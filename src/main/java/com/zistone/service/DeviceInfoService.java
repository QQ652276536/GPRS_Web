package com.zistone.service;

import com.zistone.bean.DeviceInfo;
import com.zistone.repository.DeviceInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class DeviceInfoService
{
    Logger logger = LoggerFactory.getLogger(DeviceInfoService.class);

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
                    return tempDeviceInfo.getM_akCode();
                }
            }
        }
        return "";
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
        //设备名由终端的设备编号组成,线上环境设备ID不重复故用作判断重复的条件
        DeviceInfo existDevice = m_deviceInfoRepository.FindDeviceByName(deviceInfo.getM_name());
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
            deviceInfo.setM_akCode(akCode);
            DeviceInfo tempDeviceInfo = m_deviceInfoRepository.save(deviceInfo);
            if (null != tempDeviceInfo)
            {
                logger.info(">>>设备" + tempDeviceInfo.getM_name() + "注册成功");
                return tempDeviceInfo;
            }
            else
            {
                logger.info(">>>设备注册失败!!!请检查日志排查原因...");
                return null;
            }
        }
        else
        {
            logger.info(">>>设备已存在!!!");
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
