package com.snowcattle.game.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public static String getJsonPath() {
        return jsonPath;
    }

    @Value("jsonPath")
    public static void setJsonPath(String jsonPath) {
        Config.jsonPath = jsonPath;
    }

    private static String jsonPath;

}
