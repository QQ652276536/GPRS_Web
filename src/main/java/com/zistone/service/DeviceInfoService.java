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
    private Logger m_logger = LoggerFactory.getLogger(DeviceInfoService.class);

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
    @Transactional
    public DeviceInfo InsertDevice(DeviceInfo deviceInfo)
    {
        DeviceInfo result = new DeviceInfo();
        //线上环境设备编号不会重复,所以用作判断重复的条件
        if (m_deviceInfoRepository.FindDeviceByDeviceId(deviceInfo.getM_deviceId()) == null)
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
            result = m_deviceInfoRepository.save(deviceInfo);
            if (null != result)
            {
                m_logger.info(">>>设备注册成功");
            }
            else
            {
                m_logger.error(">>>设备注册失败!!!请检查服务日志排查原因...");
            }
            return result;
        }
        else
        {
            m_logger.info(">>>设备已存在!!!");
            return result;
        }
    }

    /**
     * 更新设备
     *
     * @param deviceInfo
     * @return
     */
    public int UpdateDeviceByDeviceId(DeviceInfo deviceInfo)
    {
        return m_deviceInfoRepository
                .UpdateDeviceByDeviceId(deviceInfo.getM_deviceId(), deviceInfo.getM_lat(), deviceInfo.getM_lot(), deviceInfo.getM_height());
    }

}
