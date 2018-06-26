package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;

public class CategorySmallSearchApi extends AbstractGnaviApi {

    private CategorySmallSearchApi(String apiKey, OkHttpClient httpClient) {
        super(apiKey, httpClient);
    }

    public static CategorySmallSearchApi getInstance(String apiKey, OkHttpClient httpClient){
        return new CategorySmallSearchApi(apiKey, httpClient);
    }

    @Override
    protected String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.CategorySmallSearchUrl;
    }

    @Override
    protected String getUrlFormatJson() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    protected String getUrlFromatXml() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }
}
