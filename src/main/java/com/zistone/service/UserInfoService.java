package com.zistone.service;

import com.zistone.bean.UserInfo;
import com.zistone.repository.UserInfoRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

public class UserInfoService {

    @Resource
    private UserInfoRepository m_userInfoRepository;

    @Transactional
    public void SaveAll(List<UserInfo> userInfoList) {
        m_userInfoRepository.saveAll(userInfoList);
    }

    public UserInfo FindUserByName(String name) {
        return m_userInfoRepository.FindUserByName(name);
    }

    public List<UserInfo> FindAllUser() {
        return m_userInfoRepository.findAll();
    }

    public UserInfo InsertUser(UserInfo userInfo) {
        UserInfo existUser = m_userInfoRepository.FindUserByName(userInfo.getM_userName());
        if (existUser == null) {
            return m_userInfoRepository.save(userInfo);
        } else {
            return existUser;
        }
    }

    public UserInfo UpdateUser(UserInfo userInfo) {
        UserInfo tempUserInfo = new UserInfo();
        tempUserInfo.setM_userName(userInfo.getM_userName());
        tempUserInfo.setM_realName(userInfo.getM_userName());
        tempUserInfo.setM_phoneNumber(userInfo.getM_phoneNumber());
        tempUserInfo.setM_level(userInfo.getM_level());
        tempUserInfo.setM_isDelete(userInfo.getM_isDelete());
        return m_userInfoRepository.save(userInfo);
    }

}
