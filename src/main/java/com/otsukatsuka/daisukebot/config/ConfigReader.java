package com.otsukatsuka.daisukebot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConfigReader {

    @Autowired
    private Settings settings;

    public String getGnaviApiKey(){
        Map<String, String> map = settings.getApi();
        return map.get("gnavi-api-key");
    }
}
