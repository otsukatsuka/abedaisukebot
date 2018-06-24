package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.config.ConfigReader;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.GarbageCollectorMXBean;

@Component
public class GnaviApiClient {

    @Autowired
    ConfigReader configReader;

    public GnaviApiClient() { }

    private String getApiKey(){
        System.out.println("getApiKey");
        return configReader.getGnaviApiKey();
    }

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }

    public String getGAreaSmallSearchJson(){
        GAreaSmallSearchApi gAreaSmallSearchApi = GAreaSmallSearchApi.getInstance(getApiKey(), getHttpClient());
        return gAreaSmallSearchApi.getUrlFormatJson();
    }
}
