package com.leqiang222.base_online_shop.dao.impl;

import com.leqiang222.base_online_shop.dao.CategoryDao;
import com.leqiang222.base_online_shop.domain.Category;
import com.leqiang222.base_online_shop.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAllCats() throws SQLException {
        String sql="select * from category";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        return qr.query(sql, new BeanListHandler<Category>(Category.class));
    }

    @Override
    public void saveCat(Category c) throws SQLException {
        String sql="insert into category values (? ,?)";
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,c.getCid(),c.getCname());
    }
}
