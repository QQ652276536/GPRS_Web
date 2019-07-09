package com.zistone.repository;

import com.zistone.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    @Query("select user from UserInfo user where user.m_userName = :name")
    UserInfo FindUserByName(@Param("name") String name);

    @Query("SELECT user from UserInfo user where user.m_userName = :name and user.m_password = :pwd and user.m_isDelete = 1")
    UserInfo FindUserByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);
}
