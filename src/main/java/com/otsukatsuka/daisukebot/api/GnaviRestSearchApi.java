package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

public class GnaviRestSearchApi extends AbstractGnaviApi {

    public GnaviRestSearchApi(GnaviSearchParameters gnaviSearchParameters){
        super(gnaviSearchParameters);
    }

    @Override
    public String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GnaviRestSearchApiUrl;
    }


    @Override
    public String getUrlFormatJson() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), gnaviSearchParameters.getApiKey())
                .setAreaCodeS(gnaviSearchParameters.getAreaSCode())
                .setCategoryCodeS(gnaviSearchParameters.getCategorySCode())
                .setFreeWord(gnaviSearchParameters.getFreeWords())
                .setFreeWordCondition(Enums.FreeWordCondition.AND.getCondition())
                .setFormatType(Enums.GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    public String getUrlFromatXml() {
        throw new NotImplementedException();
    }
}
