package com.leqiang222.base_online_shop.service.impl;

import com.leqiang222.base_online_shop.dao.CategoryDao;
import com.leqiang222.base_online_shop.domain.Category;
import com.leqiang222.base_online_shop.service.CategoryService;
import com.leqiang222.base_online_shop.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");

    @Override
    public List<Category> findAllCats() throws SQLException {
        return categoryDao.findAllCats();
    }

    @Override
    public void saveCat(Category c) throws SQLException {
        categoryDao.saveCat(c);
    }
}
