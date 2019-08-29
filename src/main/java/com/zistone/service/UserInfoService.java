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
        logger.error(">>>注册失败,用户名已存在");
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
        UserInfo existUser = m_userInfoRepository.FindUserById(userInfo.getM_id());
        if (null != existUser)
        {
            if (null != userInfo.getM_userImage() && !"".equals(userInfo.getM_userImage()))
            {
                existUser.setM_userImage(userInfo.getM_userImage());
            }
            if (null != userInfo.getM_password() && !"".equals(userInfo.getM_password()))
            {
                existUser.setM_password(userInfo.getM_password());
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

}
