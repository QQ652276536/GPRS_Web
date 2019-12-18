package com.zistone.gprstest.repository;

import com.zistone.gprstest.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
{
    @Query("select user from UserInfo user where user.m_id = :id")
    UserInfo FindUserById(@Param("id") int id);

    @Query("select user from UserInfo user where user.m_userName = :name")
    UserInfo FindUserByName(@Param("name") String name);

    @Query("SELECT user from UserInfo user where user.m_userName = :name and user.m_password = :pwd and user.m_state = 1")
    UserInfo FindUserByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);
}
