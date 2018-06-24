package com.otsukatsuka.daisukebot.api;

import okhttp3.OkHttpClient;

public class GAreaSmallSearchApi {

    private final String apiKey;

    private final OkHttpClient httpClient;

    private GAreaSmallSearchApi(String apiKey, OkHttpClient httpClient){
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    public static GAreaSmallSearchApi getInstance(String apiKey, OkHttpClient httpClient){
        return new GAreaSmallSearchApi(apiKey, httpClient);
    }
}
