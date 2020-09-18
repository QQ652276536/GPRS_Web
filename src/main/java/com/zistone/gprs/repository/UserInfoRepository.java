package com.zistone.gprs.repository;

import com.zistone.gprs.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
{
    @Query("select user from UserInfo user where user.id = :id")
    UserInfo FindUserById(@Param("id") int id);

    @Query("select user from UserInfo user where user.userName = :name")
    UserInfo FindUserByName(@Param("name") String name);

    @Query("select user from UserInfo user where user.userName = :name and user.password = :pwd and user.state = 1")
    UserInfo FindUserByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);
}
