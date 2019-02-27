package com.leqiang222.base_online_shop.web.servlet;

import com.leqiang222.base_online_shop.domain.User;
import com.leqiang222.base_online_shop.service.UserService;
import com.leqiang222.base_online_shop.service.impl.UserServiceImpl;
import com.leqiang222.base_online_shop.utils.BeanFactory;
import com.leqiang222.base_online_shop.utils.BeanMapUtil;
import com.leqiang222.base_online_shop.utils.CookUtil;
import com.leqiang222.base_online_shop.utils.UUIDUtil;
import com.leqiang222.base_online_shop.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet extends BaseServlet {
    UserService userService = (UserService) BeanFactory.createObject("UserService");

    public String registerUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/register.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/login.jsp";
    }

    public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //1_接受用户数据,
        User user01 = BeanMapUtil.populate(User.class, request.getParameterMap());

        //2_部分数据是通过程序来设置的:uid,state,code
        user01.setUid(UUIDUtil.getId());
        user01.setState(0);
        user01.setCode(UUIDUtil.getId());

        //3_调用业务层注册功能,向用户邮箱发送一份激活邮件
        UserService UserService = new UserServiceImpl();
        UserService.userRegist(user01);

        //4_向客户端提示:用户注册成功,请激活,转发到提示页面
//        ${pageContext.request.contextPath}
//
//        String link = String.format("http:localhost:10300/user_servlet?method=active&code=%c", user01.getCode());
//        String msg = String.format("用户注册成功,请登录邮箱激活!(邮箱服务器未搭建，激活链接如下：%c)", link);

        String link = "http:localhost:10300/user_servlet?method=active&code=" + user01.getCode();
        String msg = "用户注册成功,请登录邮箱激活!<br/> 邮箱服务器未搭建，请点击以下激活链接进行激活：" + link;

        request.setAttribute("msg", msg);
        return  "/jsp/info.jsp";
    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接受用户名和密码
        User user01 = BeanMapUtil.populate(User.class, request.getParameterMap());
        //调用业务层登录功能
        User user02 = userService.userLogin(user01);

        if (null == user02) {
            //登录失败,向request放入提示信息,转发到登录页面,显示提示userLogin
            request.setAttribute("msg", "用户名和密码不匹配!");
            return "/jsp/login.jsp";
        }

        //登录成功,向session中放入用户信息,重定向到首页
        request.getSession().setAttribute("LOGIN_USER", user02);

        //在登录成功的基础上,判断用户是否选中自动登录复选框
        String autoLogin=request.getParameter("autoLogin");
        if("yes".equals(autoLogin)){
            //用户选中自动登录复选框
            Cookie ck=new Cookie("autoLogin",user02.getUsername()+"#"+user02.getPassword());
            ck.setPath("/online_shop");
            ck.setMaxAge(23423424);
            response.addCookie(ck);
        }

        //remUser
        String remUser=request.getParameter("remberUsername");
        if("yes".equals(remUser)){
            //用户选中自动登录复选框
            Cookie ck=new Cookie("remberUsername",user02.getUsername());
            ck.setPath("/online_shop");
            ck.setMaxAge(23423424);
            response.addCookie(ck);
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");

        return null;
    }

    public String userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //用户退出,清空用户session
        request.getSession().invalidate();
        Cookie ck= CookUtil.getCookieByName("AUTO_LOGIN", request.getCookies());
        if(null!=ck){
            ck.setMaxAge(0);
            ck.setPath("/online_shop");
            response.addCookie(ck);
        }

        response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        return null;
    }

    //active
    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
        String code=request.getParameter("code");
        //调用业务层功能:根据激活码查询用户 select * from user where code=?
        User user01 = userService.userActive(code);
        //如果用户不为空,激活码正确的,可以激活
        if(null!=user01){
            user01.setState(1);
            user01.setCode("");
            userService.updateUser(user01);
        }
        //转发到登录页面,提示:激活成功,请登录
        request.setAttribute("msg", "用户激活成功,请登录");
        return "/jsp/login.jsp";

    }
}
