package com.leqiang222.base_online_shop.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leqiang222.base_online_shop.domain.Category;
import com.leqiang222.base_online_shop.service.CategoryService;
import com.leqiang222.base_online_shop.service.impl.CategoryServiceImpl;
import com.leqiang222.base_online_shop.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseServlet {
    private static ObjectMapper MAPPER = new ObjectMapper();

    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=utf-8");

        //查询所有分类
        CategoryService CategoryService = new CategoryServiceImpl();
        List<Category> list = CategoryService.findAllCats();

//        String jsonStr = JSONArray.fromObject(list).toString();
        String jsonStr = MAPPER.writeValueAsString(list);

        //将字符串数据响应到客户端
        response.getWriter().println(jsonStr);

        return null;
    }
}
