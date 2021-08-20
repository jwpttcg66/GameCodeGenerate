package com.snowcattle.game.code.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * 游戏启动参数
 */
public class EnvParam {

    /**
     * 返回环境变量
     * @param key
     * @return
     */
    public static String getParam(String key){
        String result = System.getenv(key);
        if(result == null)
        {
            result = System.getProperty(key);
        }


        return result;
    }
}
