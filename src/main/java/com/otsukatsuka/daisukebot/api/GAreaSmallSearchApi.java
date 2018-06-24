package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.Consts;
import com.otsukatsuka.daisukebot.Enums.GnaviApiFormatType;
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

    public String getUrlFormatJson(){
        return new GnaviApiUrlBuilder
                .Builder(Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl, this.apiKey)
                .setFormatType(GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    public String getUrlFromatXml(){
        return new GnaviApiUrlBuilder
                .Builder(Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl, this.apiKey)
                .setFormatType(GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }
}
