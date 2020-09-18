package com.zistone.gprs.service;

import com.zistone.gprs.bean.UserInfo;
import com.zistone.gprs.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    @Resource
    private UserInfoRepository _userInfoRepository;

    /**
     * 登录
     *
     * @param userInfo
     * @return
     */
    public UserInfo Login(UserInfo userInfo) {
        return _userInfoRepository.FindUserByNameAndPwd(userInfo.getUserName(), userInfo.getPassword());
    }

    @Transactional
    public void SaveList(List<UserInfo> userInfoList) {
        _userInfoRepository.saveAll(userInfoList);
    }

    public List<UserInfo> FindAllUser() {
        return _userInfoRepository.findAll();
    }

    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    public UserInfo Insert(UserInfo userInfo) {
        UserInfo existUser = _userInfoRepository.FindUserByName(userInfo.getUserName());
        if (existUser == null) {
            return _userInfoRepository.save(userInfo);
        }
        LOGGER.error("注册失败,用户名已存在\r\n");
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    public UserInfo Update(UserInfo userInfo) {
        UserInfo existUser = _userInfoRepository.FindUserById(userInfo.getId());
        if (null != existUser) {
            if (null != userInfo.getUserImage() && !"".equals(userInfo.getUserImage())) {
                LOGGER.info("此次用户信息更新有图片");
                existUser.setUserImage(userInfo.getUserImage());
            }
            if (null != userInfo.getPassword() && !"".equals(userInfo.getPassword())) {
                existUser.setPassword(userInfo.getPassword());
            }
            return _userInfoRepository.save(existUser);
        }
        LOGGER.error("用户信息更新失败\r\n");
        return null;
    }

    public void DelUserById(Integer id) {
        _userInfoRepository.deleteById(id);
    }

}
