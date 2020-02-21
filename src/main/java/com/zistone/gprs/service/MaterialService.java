package com.zistone.gprs.service;

import com.zistone.gprs.bean.Material;
import com.zistone.gprs.repository.MaterialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MaterialService
{
    private Logger _logger = LoggerFactory.getLogger(MaterialService.class);

    @Resource
    private MaterialRepository _materialRepository;

    /**
     * 更新物料
     *
     * @param material
     * @return
     */
    public Material Update(Material material)
    {
        return _materialRepository.save(material);
    }

    /**
     * 根据设备地址查找物料
     * @param deviceAddress
     * @return
     */
    public Material FindByDeviceAddress(String deviceAddress)
    {
        return _materialRepository.FindByDeviceAddress(deviceAddress);
    }
}
