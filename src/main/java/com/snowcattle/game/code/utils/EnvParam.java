package com.snowcattle.game.code.utils;

import com.snowcattle.game.code.generate.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 游戏启动参数
 */
@Service
public class EnvParam {

    @Autowired
    private static ConfigBean configBean;
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

    public static String getJsonPath(){
        return configBean.getJsonPath();
    }
}
