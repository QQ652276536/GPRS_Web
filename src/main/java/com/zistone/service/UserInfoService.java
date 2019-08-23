package com.zistone.service;

import com.zistone.bean.UserInfo;
import com.zistone.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserInfoService
{
    Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Resource
    private UserInfoRepository m_userInfoRepository;

    /**
     * 登录
     *
     * @param userInfo
     * @return
     */
    public UserInfo Login(UserInfo userInfo)
    {
        return m_userInfoRepository.FindUserByNameAndPwd(userInfo.getM_userName(), userInfo.getM_password());
    }

    @Transactional
    public void SaveList(List<UserInfo> userInfoList)
    {
        m_userInfoRepository.saveAll(userInfoList);
    }

    public UserInfo FindUserByName(String name)
    {
        return m_userInfoRepository.FindUserByName(name);
    }

    public List<UserInfo> FindAllUser()
    {
        return m_userInfoRepository.findAll();
    }

    /**
     * 注册
     *
     * @param userInfo
     * @return
     */
    public UserInfo Insert(UserInfo userInfo)
    {
        UserInfo existUser = m_userInfoRepository.FindUserByName(userInfo.getM_userName());
        if (existUser == null)
        {
            return m_userInfoRepository.save(userInfo);
        }
        logger.error(">>>注册失败");
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    public UserInfo Update(UserInfo userInfo)
    {
        UserInfo existUser = m_userInfoRepository.FindUserByName(userInfo.getM_userName());
        if (null != existUser)
        {
            existUser.setM_realName(userInfo.getM_realName());
            existUser.setM_phoneNumber(userInfo.getM_phoneNumber());
            existUser.setM_password(userInfo.getM_password());
            if (null != userInfo.getM_userImage() && !"".equals(userInfo.getM_userImage()))
            {
                logger.info(">>>此次更新有图片");
                existUser.setM_userImage(userInfo.getM_userImage());
            }
            return m_userInfoRepository.save(existUser);
        }
        logger.error(">>>更新失败");
        return null;
    }

    public void DelUserById(Integer id)
    {
        m_userInfoRepository.deleteById(id);
    }

    public UserInfo DelUserByName(String name)
    {
        UserInfo tempUser = m_userInfoRepository.FindUserByName(name);
        if (tempUser != null)
        {
            m_userInfoRepository.delete(tempUser);
        }
        return tempUser;
    }

}
