package com.leqiang222.base_online_shop.service;

import com.leqiang222.base_online_shop.domain.Product;
import com.leqiang222.base_online_shop.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findNewProducts()throws SQLException;

    List<Product> findHotProducts()throws SQLException ;

    Product findProductByPid(String pid)throws SQLException ;

    PageModel findProductsWithCidAndPage(String cid, int curNum)throws SQLException ;

    void saveProduct(Product product)throws SQLException ;
}
