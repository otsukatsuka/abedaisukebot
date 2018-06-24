package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import com.otsukatsuka.daisukebot.core.Enums.GnaviApiFormatType;
import okhttp3.OkHttpClient;

import java.io.IOException;


public class GAreaSmallSearchApi extends AbstractGnaviApi{

    @Override
    protected String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl;
    }

    private GAreaSmallSearchApi(String apiKey, OkHttpClient httpClient){
        super(apiKey, httpClient);
    }

    public static GAreaSmallSearchApi getInstance(String apiKey, OkHttpClient httpClient){
        return new GAreaSmallSearchApi(apiKey, httpClient);
    }

    @Override
    protected String getUrlFormatJson(){
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    protected String getUrlFromatXml(){
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }
}
