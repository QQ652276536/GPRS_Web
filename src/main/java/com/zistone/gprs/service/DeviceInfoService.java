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
    private Logger _logger = LoggerFactory.getLogger(DeviceInfoService.class);

    @Resource
    private DeviceInfoRepository _deviceInfoRepository;
    @Resource
    private LocationInfoRepository _locationInfoRepository;

    /**
     * 根据鉴权码查找设备
     *
     * @param akCode
     * @return
     */
    public DeviceInfo FindByAKCode(String akCode)
    {
        return _deviceInfoRepository.FindByAKCode(akCode);
    }

    @Transactional
    public void SaveList(List<DeviceInfo> deviceInfoList)
    {
        _deviceInfoRepository.saveAll(deviceInfoList);
    }

    /**
     * 根据编号查找设备
     *
     * @param id
     * @return
     */
    public DeviceInfo FindById(int id)
    {
        return _deviceInfoRepository.FindById(id);
    }

    /**
     * 查询所有的设备
     *
     * @return
     */
    public List<DeviceInfo> FindAllDevice()
    {
        return _deviceInfoRepository.findAll();
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
        DeviceInfo queryDevice = _deviceInfoRepository.FindByDeviceId(deviceInfo.getDeviceId());
        //线上环境设备编号不会重复,所以用作判断重复的条件
        //根据设备编号查找设备,没有则新增
        if (queryDevice == null)
        {
            _logger.info(String.format(">>>设备%s不存在,新增该设备...", deviceInfo.getDeviceId()));
            deviceInfo.setAkCode(akCode);
            DeviceInfo tempDevice = _deviceInfoRepository.save(deviceInfo);
            if (null != tempDevice && tempDevice.getId() != 0)
            {
                _logger.info(">>>设备注册成功");
            }
            else
            {
                _logger.error(String.format(">>>设备%s注册失败!!!请检查服务日志排查原因...\r\n", deviceInfo.getDeviceId()));
            }
            return tempDevice;
        }
        //有则更新
        else
        {
            _logger.info(String.format(">>>设备%s已存在,更新该设备...", queryDevice.getDeviceId()));
            int num = _deviceInfoRepository
                    .UpdateByDeviceId(deviceInfo.getDeviceId(), deviceInfo.getLat(), deviceInfo.getLot(),
                            deviceInfo
                                    .getHeight(), deviceInfo.getTemperature(), deviceInfo.getElectricity());
            if (num == 1)
            {
                _logger.info(">>>设备更新成功");
            }
            else
            {
                _logger.error(String.format(">>>设备%s更新失败!!!请检查服务日志排查原因...\r\n", queryDevice.getDeviceId()));
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
        locationInfo.setDeviceId(deviceInfo.getDeviceId());
        locationInfo.setLat(deviceInfo.getLat());
        locationInfo.setLot(deviceInfo.getLot());
        locationInfo.setHeight(deviceInfo.getHeight());
        _locationInfoRepository.save(locationInfo);
        return _deviceInfoRepository.save(deviceInfo);
    }

    /**
     * 更新设备位置信息
     *
     * @param deviceInfo
     * @return 受影响的行数
     */
    public int UpdateLocationByDeviceId(DeviceInfo deviceInfo)
    {
        return _deviceInfoRepository
                .UpdateLocationByDeviceId(deviceInfo.getDeviceId(), deviceInfo.getLat(), deviceInfo.getLot(),
                        deviceInfo
                                .getHeight());
    }

}
