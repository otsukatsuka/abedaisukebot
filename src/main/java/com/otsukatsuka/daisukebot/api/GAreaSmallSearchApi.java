package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;

import java.util.Optional;


public class GAreaSmallSearchApi extends GnaviApiBase {

    @Override
    public String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl;
    }

    private GAreaSmallSearchApi(String apiKey, OkHttpClient httpClient){
        super(apiKey, httpClient);
    }

    @Override
    public String getUrlFormatJson(){
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    public String getUrlFromatXml(){
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }
}
