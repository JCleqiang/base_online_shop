package com.leqiang222.base_online_shop.utils;

import java.util.UUID;

public class UUIDUtil {
    /**
     * 随机生成id
     * @return
     */
    public static String getId(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }


    public static String getUUID64(){
        return getId()+getId();
    }

    /**
     * 生成随机码
     * @return
     */
    public static String getCode(){
        return getId();
    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}
