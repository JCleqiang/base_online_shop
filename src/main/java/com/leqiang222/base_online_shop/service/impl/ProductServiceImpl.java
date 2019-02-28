package com.leqiang222.base_online_shop.service.impl;

import com.leqiang222.base_online_shop.dao.ProductDao;
import com.leqiang222.base_online_shop.domain.Product;
import com.leqiang222.base_online_shop.service.ProductService;
import com.leqiang222.base_online_shop.utils.BeanFactory;
import com.leqiang222.base_online_shop.utils.PageModel;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao = (ProductDao) BeanFactory.createObject("ProductDao");

    @Override
    public List<Product> findNewProducts() throws SQLException {
        return productDao.findNewProducts();
    }

    @Override
    public List<Product> findHotProducts() throws SQLException {
        return productDao.findHotProducts();
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        return productDao.findProductByPid(pid);
    }

    @Override
    public PageModel findProductsWithCidAndPage(String cid, int curNum) throws SQLException {
        //1_创建PageModel对象,目的:携带分页参数 select count(*) from product where cid=?
        int totalRecords= productDao.findTotalNum(cid);
        PageModel pm=new PageModel(curNum, totalRecords, 12);
        //2_关联集合 SELECT * FROM product WHERE cid=1 LIMIT (当前页-1)*5,5;
        List<Product> list=productDao.findProductsWithCidAndPage(cid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);
        //3_关联url
        pm.setUrl("product_servlet?method=findProductsWithCidAndPage&cid="+cid);
        return pm;
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
        productDao.saveProduct(product);
    }
}
