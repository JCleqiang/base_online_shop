package com.leqiang222.base_online_shop.dao.impl;

import com.leqiang222.base_online_shop.dao.UserDao;
import com.leqiang222.base_online_shop.domain.User;
import com.leqiang222.base_online_shop.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByUsreName(String um) throws SQLException {
        return null;
    }

    @Override
    public void userRegist(User user01) throws SQLException {
        String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {user01.getUid(),user01.getUsername(),user01.getPassword(),user01.getName(),user01.getEmail(),user01.getTelephone(),user01.getBirthday(),user01.getSex(),user01.getState(),user01.getCode()};
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql, params);
    }

    @Override
    public User userActive(String code) throws SQLException {
        String sql = "select * from user where code =?";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanHandler<User>(User.class),code);
    }

    @Override
    public void updateUser(User user01) throws SQLException {
        String sql="UPDATE USER SET username= ? ,PASSWORD=? ,NAME =? ,email =? ,telephone =? , birthday =?  ,sex =? ,state= ? , CODE = ? WHERE uid=?";
        Object[] params={user01.getUsername(),user01.getPassword(),user01.getName(),user01.getEmail(),user01.getTelephone(),user01.getBirthday(),user01.getSex(),user01.getState(),user01.getCode(),user01.getUid()};
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,params);
    }

    @Override
    public User userLogin(User user01) throws SQLException {
        String sql = "select * from user where username =? and password=? and state=1";
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

        User user = queryRunner.query(sql, new BeanHandler<User>(User.class), user01.getUsername(), user01.getPassword());

        return user;
    }
}
