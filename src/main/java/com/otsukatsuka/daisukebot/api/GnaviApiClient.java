package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.config.ApiConfig;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GnaviApiClient {

    @Autowired
    ApiConfig apiConfig;

    public GnaviApiClient() { }

    private String getApiKey(){
        System.out.println("apiConfig = " + apiConfig);
        String key = apiConfig.getGnaviApiKey();
        System.out.println("apikey = " + key);

        return key;
    }

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }

    public String getGAreaSmallSearchJson(){
        GAreaSmallSearchApi gAreaSmallSearchApi = GAreaSmallSearchApi.getInstance(getApiKey(), getHttpClient());
        return gAreaSmallSearchApi.getUrlFormatJson();
    }
}
