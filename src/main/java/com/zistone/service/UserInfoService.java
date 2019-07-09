package com.zistone.service;

import com.zistone.bean.UserInfo;
import com.zistone.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserInfoService {

    @Resource
    private UserInfoRepository m_userInfoRepository;

    /**
     * 登录
     *
     * @param userInfo
     * @return
     */
    public UserInfo Login(UserInfo userInfo) {
        return m_userInfoRepository.FindUserByNameAndPwd(userInfo.getM_userName(), userInfo.getM_password());
    }

    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    public UserInfo Register(UserInfo userInfo) {
        return InsertUser(userInfo);

    }

    @Transactional
    public void SaveList(List<UserInfo> userInfoList) {
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
        tempUserInfo.setM_state(userInfo.getM_state());
        return m_userInfoRepository.save(userInfo);
    }

    public void DelUserById(Integer id) {
        m_userInfoRepository.deleteById(id);
    }

    public UserInfo DelUserByName(String name) {
        UserInfo tempUser = m_userInfoRepository.FindUserByName(name);
        if (tempUser != null) {
            m_userInfoRepository.delete(tempUser);
        }
        return tempUser;
    }

}
