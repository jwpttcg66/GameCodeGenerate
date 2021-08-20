package com.snowcattle.game.code.utils;

import com.snowcattle.game.code.generate.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.support.UiApplicationContextUtils;

/**
 * 游戏启动参数
 */
public class EnvParam {

    public static ConfigBean getConfigBean() {
        return configBean;
    }

    public static void setConfigBean(ConfigBean configBean) {
        EnvParam.configBean = configBean;
    }

    public static ConfigBean configBean;


    /**
     * 返回环境变量
     * @param key
     * @return
     */
    public  String getParam(String key){
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
