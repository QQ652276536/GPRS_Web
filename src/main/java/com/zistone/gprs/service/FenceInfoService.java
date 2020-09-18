package com.zistone.gprs.service;

import com.zistone.gprs.bean.FenceInfo;
import com.zistone.gprs.repository.FenceInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FenceInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenceInfoService.class);

    @Resource
    private FenceInfoRepository _fenceInfoRepository;

    public List<FenceInfo> FindByDeviceId(String deviceId) {
        List<FenceInfo> list = _fenceInfoRepository.FindByDeviceId(deviceId);
        return list;
    }

    public FenceInfo InsertByDeviceId(FenceInfo fenceInfo) {
        return _fenceInfoRepository.save(fenceInfo);
    }

    public void DelById(String id) {
        _fenceInfoRepository.deleteById(Integer.valueOf(id));
    }

}
