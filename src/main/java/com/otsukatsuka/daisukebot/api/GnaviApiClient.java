package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.config.ConfigReader;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

public class GnaviApiClient {

    private final GAreaSmallSearchApi gAreaSmallSearchApi;

    private GnaviApiClient(){
        gAreaSmallSearchApi = GAreaSmallSearchApi.getInstance(getApiKey(), getHttpClient());
    }

    public static GnaviApiClient getInstance(){
        return new GnaviApiClient();
    }

    private String getApiKey(){
        return new ConfigReader().getGnaviApiKey();
    }

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }

    public String getGAreaSmallSearchJson(){
        return gAreaSmallSearchApi.getUrlFormatJson();
    }
}
