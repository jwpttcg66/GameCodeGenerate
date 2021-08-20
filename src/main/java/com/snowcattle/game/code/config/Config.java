package com.snowcattle.game.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
public class Config {

    public Config() {
        System.out.println("Config初始化...");
    }

    @Autowired
    Environment environment;

    public String getJsonPath() {
        return jsonPath;
    }

    @Value("${jsonPath}")
    public  void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    private  String jsonPath;

}
