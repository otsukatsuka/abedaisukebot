package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;

public class GnaviRestSearchApi extends AbstractGnaviApi{

    @Override
    protected String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GnaviRestSearchApiUrl;
    }

    private GnaviRestSearchApi(String apiKey, OkHttpClient okHttpClient){
        super(apiKey, okHttpClient);
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

    public GnaviRestSearchApi getInstance(String apiKey, OkHttpClient okHttpClient){
        return new GnaviRestSearchApi(apiKey, okHttpClient);
    }

}
