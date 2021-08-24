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

    /**
     * java sql文件生成路径
     */
    private String javaSqlPoPath;


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

    public String getJavaSqlPoPath() {
        return javaSqlPoPath;
    }

    @Value("${javaSqlPoPath}")
    public void setJavaSqlPoPath(String javaSqlPoPath) {
        this.javaSqlPoPath = javaSqlPoPath;
    }

    private String xmlPath;
    private String xmlJsonPath;
    private String xmlJavaDictPoPath;
    private String xmlJavaDictPackage;

    public String getXmlPath() {
        return xmlPath;
    }
    @Value("${xmlPath}")
    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getXmlJsonPath() {
        return xmlJsonPath;
    }

    @Value("${xmlJsonPath}")
    public void setXmlJsonPath(String xmlJsonPath) {
        this.xmlJsonPath = xmlJsonPath;
    }

    public String getXmlJavaDictPoPath() {
        return xmlJavaDictPoPath;
    }

    @Value("${xmlJavaDictPoPath}")
    public void setXmlJavaDictPoPath(String xmlJavaDictPoPath) {
        this.xmlJavaDictPoPath = xmlJavaDictPoPath;
    }

    public String getXmlJavaDictPackage() {
        return xmlJavaDictPackage;
    }
    @Value("${xmlJavaDictPackage}")
    public void setXmlJavaDictPackage(String xmlJavaDictPackage) {
        this.xmlJavaDictPackage = xmlJavaDictPackage;
    }
}

