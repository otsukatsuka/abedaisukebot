package com.otsukatsuka.daisukebot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "settings.api")
public class ApiConfig {

    private String gnaviApiKey;

    public String getGnaviApiKey(){
        return this.gnaviApiKey;
    }

    public void setGnaviApiKey(String gnaviApiKey){
        this.gnaviApiKey = gnaviApiKey;
    }
}
