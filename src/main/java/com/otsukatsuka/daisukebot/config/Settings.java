package com.otsukatsuka.daisukebot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "settings")
public class Settings {

    private String  settings;
    private Map<String, String> api;

    public String getApiKey(){
        return settings;
    }

    public Map<String, String> getApi(){
        return api;
    }
}
