package com.zistone.gprs.service;

import com.zistone.gprs.bean.FenceInfo;
import com.zistone.gprs.repository.FenceInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FenceInfoService
{
    private Logger m_logger = LoggerFactory.getLogger(FenceInfoService.class);

    @Resource
    private FenceInfoRepository m_fenceInfoRepository;

    public List<FenceInfo> FindByDeviceId(String deviceId)
    {
        List<FenceInfo> list = m_fenceInfoRepository.FindByDeviceId(deviceId);
        return list;
    }

    public FenceInfo InsertByDeviceId(FenceInfo fenceInfo)
    {
        return m_fenceInfoRepository.save(fenceInfo);
    }

    public void DelById(String id)
    {
        m_fenceInfoRepository.deleteById(Integer.valueOf(id));
    }
}
