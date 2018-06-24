package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.config.ConfigReader;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GnaviApiClient {

    @Autowired
    ConfigReader configReader;

    private final GAreaSmallSearchApi gAreaSmallSearchApi;

    private GnaviApiClient(){
        gAreaSmallSearchApi = GAreaSmallSearchApi.getInstance(getApiKey(), getHttpClient());
    }

    public static GnaviApiClient getInstance(){
        return new GnaviApiClient();
    }

    private String getApiKey(){
        System.out.println("getApiKey");
        return configReader.getGnaviApiKey();
    }

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }

    public String getGAreaSmallSearchJson(){
        return gAreaSmallSearchApi.getUrlFormatJson();
    }
}
