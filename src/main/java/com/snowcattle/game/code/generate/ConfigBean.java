package com.snowcattle.game.code.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConfigurationProperties
public class ConfigBean {
    public ConfigBean(){
        System.out.println("初始化");
    }

    @Autowired
    Environment environment;

    public String getJsonPath() {
        return jsonPath;
    }

    @Value("${jsonPath}")
    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    private String jsonPath;
}

