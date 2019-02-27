package com.leqiang222.base_online_shop.dao;

import com.leqiang222.base_online_shop.domain.User;

import java.sql.SQLException;

public interface UserDao {
    // 用户名 查找用户
    User findUserByUsreName(String um) throws SQLException;

    // 注册用户
    void userRegist(User user01)throws SQLException;

    //
    User userActive(String code)throws SQLException;

    // 更新用户
    void updateUser(User user01)throws SQLException;

    // 登录
    User userLogin(User user01)throws SQLException;
}
