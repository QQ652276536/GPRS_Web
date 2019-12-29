package com.zistone.gprs.service;

import com.zistone.gprs.bean.DeviceInfo;
import com.zistone.gprs.bean.LocationInfo;
import com.zistone.gprs.repository.DeviceInfoRepository;
import com.zistone.gprs.repository.LocationInfoRepository;
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
    @Resource
    private LocationInfoRepository m_locationInfoRepository;

    /**
     * 根据鉴权码查找设备
     *
     * @param akCode
     * @return
     */
    public DeviceInfo FindByAKCode(String akCode)
    {
        return m_deviceInfoRepository.FindByAKCode(akCode);
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
    public DeviceInfo FindById(int id)
    {
        return m_deviceInfoRepository.FindById(id);
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
     * 新增一台设备,不允许设备ID重复
     *
     * @param deviceInfo
     * @return
     */
    @Transactional
    public DeviceInfo InsertByDeviceId(DeviceInfo deviceInfo)
    {
        //鉴权码
        String akCode = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++)
        {
            int num = random.nextInt(3) % 4;
            switch (num)
            {
                //随机大写字母
                case 0:
                    akCode += (char) (random.nextInt(26) + 65);
                    break;
                //随机小写字母
                case 1:
                    akCode += (char) (random.nextInt(26) + 97);
                    break;
                //随机数字
                case 2:
                    akCode += random.nextInt(10);
                    break;
                default:
                    break;
            }
        }
        DeviceInfo queryDevice = m_deviceInfoRepository.FindByDeviceId(deviceInfo.getM_deviceId());
        //线上环境设备编号不会重复,所以用作判断重复的条件
        //根据设备编号查找设备,没有则新增
        if (queryDevice == null)
        {
            m_logger.info(String.format(">>>设备%s不存在,新增该设备...", deviceInfo.getM_deviceId()));
            deviceInfo.setM_akCode(akCode);
            DeviceInfo tempDevice = m_deviceInfoRepository.save(deviceInfo);
            if (null != tempDevice && tempDevice.getM_id() != 0)
            {
                m_logger.info(">>>设备注册成功");
            }
            else
            {
                m_logger.error(String.format(">>>设备%s注册失败!!!请检查服务日志排查原因...\r\n", deviceInfo.getM_deviceId()));
            }
            return tempDevice;
        }
        //有则更新
        else
        {
            m_logger.info(String.format(">>>设备%s已存在,更新该设备...", queryDevice.getM_deviceId()));
            int num = m_deviceInfoRepository
                    .UpdateByDeviceId(deviceInfo.getM_deviceId(), deviceInfo.getM_lat(), deviceInfo.getM_lot(),
                            deviceInfo
                                    .getM_height(), deviceInfo.getM_temperature(), deviceInfo.getM_electricity());
            if (num == 1)
            {
                m_logger.info(">>>设备更新成功");
            }
            else
            {
                m_logger.error(String.format(">>>设备%s更新失败!!!请检查服务日志排查原因...\r\n", queryDevice.getM_deviceId()));
            }
            return deviceInfo;
        }
    }

    /**
     * 更新设备
     *
     * @param deviceInfo
     * @return
     */
    public DeviceInfo Update(DeviceInfo deviceInfo)
    {
        //更新设备表的同时要将当前位置信息插入至轨迹表
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setM_deviceId(deviceInfo.getM_deviceId());
        locationInfo.setM_lat(deviceInfo.getM_lat());
        locationInfo.setM_lot(deviceInfo.getM_lot());
        locationInfo.setM_height(deviceInfo.getM_height());
        m_locationInfoRepository.save(locationInfo);
        return m_deviceInfoRepository.save(deviceInfo);
    }

    /**
     * 更新设备位置信息
     *
     * @param deviceInfo
     * @return 受影响的行数
     */
    public int UpdateLocationByDeviceId(DeviceInfo deviceInfo)
    {
        return m_deviceInfoRepository
                .UpdateLocationByDeviceId(deviceInfo.getM_deviceId(), deviceInfo.getM_lat(), deviceInfo.getM_lot(),
                        deviceInfo
                                .getM_height());
    }

}
