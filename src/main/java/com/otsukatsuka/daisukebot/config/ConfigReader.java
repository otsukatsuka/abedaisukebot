package com.otsukatsuka.daisukebot.config;

import org.springframework.beans.factory.annotation.Value;

public class ConfigReader {

    @Value("${settings.api.gnavi-api-key}")
    private String gnaviApiKey;

    public String getGnaviApiKey(){
        return this.gnaviApiKey;
    }
}
