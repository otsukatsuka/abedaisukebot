package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.config.ConfigReader;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

public class GnaviApiClient {

    @Autowired
    private ConfigReader configReader;

    private final GAreaSmallSearchApi gAreaSmallSearchApi;

    private GnaviApiClient(){
        gAreaSmallSearchApi = GAreaSmallSearchApi.getInstance(getApiKey(), getHttpClient());
    }

    public static GnaviApiClient getInstance(){
        return new GnaviApiClient();
    }

    private String getApiKey(){
        return configReader.getGnaviApiKey();
    }

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }
}
