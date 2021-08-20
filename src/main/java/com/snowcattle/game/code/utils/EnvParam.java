package com.snowcattle.game.code.utils;

import com.snowcattle.game.code.generate.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 游戏启动参数
 */
@Component
public class EnvParam {

    public EnvParam(){
        System.out.println("EnvParam 初始化");
    }


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
