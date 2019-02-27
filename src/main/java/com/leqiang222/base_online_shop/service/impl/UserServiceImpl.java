package com.leqiang222.base_online_shop.service.impl;

import com.leqiang222.base_online_shop.dao.UserDao;
import com.leqiang222.base_online_shop.domain.User;
import com.leqiang222.base_online_shop.service.UserService;
import com.leqiang222.base_online_shop.utils.BeanFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");

    @Override
    public User findUserByUsreName(String um) throws SQLException {
        return null;
    }

    @Override
    public void userRegist(User user01) throws SQLException {
        //3_调用业务层注册功能
        userDao.userRegist(user01);
//        try {
//            //向用户邮箱发送一份激活邮件
//            MailUtils.sendMail(user01.getEmail(),user01.getCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public User userActive(String code) throws SQLException {
        return userDao.userActive(code);
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        userDao.updateUser(user01);
    }

    @Override
    public User userLogin(User user01) throws SQLException {
        return userDao.userLogin(user01);
    }
}
