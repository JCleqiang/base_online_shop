package com.leqiang222.base_online_shop.service;

import com.leqiang222.base_online_shop.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<Category> findAllCats()throws SQLException;

    void saveCat(Category c)throws SQLException;
}
