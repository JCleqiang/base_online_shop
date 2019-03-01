package com.leqiang222.base_online_shop.web.servlet;

import com.leqiang222.base_online_shop.domain.Category;
import com.leqiang222.base_online_shop.domain.Product;
import com.leqiang222.base_online_shop.service.CategoryService;
import com.leqiang222.base_online_shop.service.ProductService;
import com.leqiang222.base_online_shop.service.impl.CategoryServiceImpl;
import com.leqiang222.base_online_shop.service.impl.ProductServiceImpl;
import com.leqiang222.base_online_shop.utils.PageModel;
import com.leqiang222.base_online_shop.utils.UUIDUtil;
import com.leqiang222.base_online_shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductServlet extends BaseServlet {
    // 商品id查询商品
    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //服务端获取到pid
        String pid=request.getParameter("pid");
        //根据pid查询对应的商品信息,
        ProductService productService=new ProductServiceImpl();
        Product pro=productService.findProductByPid(pid);
        //将商品放入request,
        request.setAttribute("PRODUCT", pro);

        //
        CategoryService categoryService = new CategoryServiceImpl();
        Category category = categoryService.findProductByCid(pro.getCid());

        request.getSession().setAttribute("PRODUCT_CATEGORY", category);


        //
        String ranStr= UUIDUtil.getId();
        //在session存放一份随机字符串
        request.getSession().setAttribute("ranStr",ranStr);
        System.out.println(ranStr);

        //转发到product_info.jsp
        return "/jsp/product_info.jsp";
    }

    //findProductsWithCidAndPage
    public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接受当前页
        int curNum=Integer.parseInt(request.getParameter("num"));
        //接受分类cid
        String cid=request.getParameter("cid");
        //调用业务层查询当前分类下的当前页数据功能,返回PageModel对象
        ProductService ProductService=new ProductServiceImpl();
        PageModel pm=ProductService.findProductsWithCidAndPage(cid,curNum);
        //将PageModel对象存入到request
        request.setAttribute("page", pm);
        //转发到"/jsp/product_list.jsp"
        return "/jsp/product_list.jsp";
    }
}
