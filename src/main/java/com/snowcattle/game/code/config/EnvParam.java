package com.snowcattle.game.code.config;

import com.snowcattle.game.code.generate.ConfigBean;

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

    public static String getxlsPath(){
        return configBean.getXlsPath();
    }
    public static String getJavaDictPath(){
        return configBean.getJavaDictPoPath();
    }

    public static String getJavaDictPackage(){
        return configBean.getJavaDictPackage();
    }

}
