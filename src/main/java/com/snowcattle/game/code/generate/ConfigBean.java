package com.snowcattle.game.code.generate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
public class ConfigBean {

    public String getJsonPath() {
        return jsonPath;
    }

    @Value("${jsonPath}")
    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    private String jsonPath;
}

