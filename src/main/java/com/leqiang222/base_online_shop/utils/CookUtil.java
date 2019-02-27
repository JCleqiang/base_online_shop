package com.leqiang222.base_online_shop.utils;

import javax.servlet.http.Cookie;

public class CookUtil {
    /**
     * 通过名称在cookie数组获取指定的cookie
     * @param name cookie名称
     * @param cookies  cookie数组
     * @return
     */
    public static Cookie getCookieByName(String name, Cookie[] cookies) {
        if(cookies==null){
            return null;
        }

        for (Cookie c : cookies) {
            //通过名称获取
            if(name.equals(c.getName())){
                return c;
            }
        }
        return null;
    }
}
