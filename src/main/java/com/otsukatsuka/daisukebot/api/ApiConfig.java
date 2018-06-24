package com.otsukatsuka.daisukebot.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiConfig {

    private String key;

    public String getKey(){
        return this.key;
    }

    public void setKey(String key){
        this.key = key;
    }
}
