package com.otsukatsuka.daisukebot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigReader {

    @Value("${settings.api.gnavi-api-key}")
    private String gnaviApiKey;

    public String getGnaviApiKey(){
        System.out.println("apikey = " + gnaviApiKey);
        return this.gnaviApiKey;
    }

    public void setGnaviApiKey(String gnaviApiKey){
        this.gnaviApiKey = gnaviApiKey;
    }
}
