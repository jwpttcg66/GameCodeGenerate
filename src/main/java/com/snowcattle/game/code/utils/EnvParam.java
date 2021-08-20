package com.snowcattle.game.code.utils;

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
