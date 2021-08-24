package com.snowcattle.game.code.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties
public class ConfigBean {
    public ConfigBean(){
        System.out.println("配置初始化");
    }


    /**
     * json文件夹路径
     */
    private String jsonPath;

    /**
     * sql文件夹路径
     */
    private String sqlPath;

    /**
     * sqlPo默认包路径
     */
    private String javaSqlPoDictPackage;
    /**
     *  excel目录
     */
    private String xlsPath;

    /**
     * java生成的数据字典目录
     */
    private String javaDictPoPath;

    /**
     * java生成的包名
     */
    private String javaDictPackage;



    public String getJsonPath() {
        return jsonPath;
    }

    @Value("${jsonPath}")
    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }



    public String getXlsPath() {
        return xlsPath;
    }

    @Value("${xlsPath}")
    public void setXlsPath(String xlsPath) {
        this.xlsPath = xlsPath;
    }


    public String getJavaDictPoPath() {
        return javaDictPoPath;
    }

    @Value("${javaDictPoPath}")
    public void setJavaDictPoPath(String javaDictPoPath) {
        this.javaDictPoPath = javaDictPoPath;
    }

    public String getJavaDictPackage() {
        return javaDictPackage;
    }

    @Value("${javaDictPackage}")
    public void setJavaDictPackage(String javaDictPackage) {
        this.javaDictPackage = javaDictPackage;
    }

    public String getSqlPath() {
        return sqlPath;
    }

    @Value("${sqlPath}")
    public void setSqlPath(String sqlPath) {
        this.sqlPath = sqlPath;
    }

    public String getJavaSqlPoDictPackage() {
        return javaSqlPoDictPackage;
    }

    @Value("${javaSqlPoDictPackage}")
    public void setJavaSqlPoDictPackage(String javaSqlPoDictPackage) {
        this.javaSqlPoDictPackage = javaSqlPoDictPackage;
    }
}

