package com.leqiang222.base_online_shop.dao;

import com.leqiang222.base_online_shop.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> findAllCats()throws SQLException;

    void saveCat(Category c)throws SQLException;

    Category findProductByCid(String cid) throws SQLException;
}
