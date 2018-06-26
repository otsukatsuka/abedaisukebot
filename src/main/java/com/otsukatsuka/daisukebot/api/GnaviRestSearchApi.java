package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;

import java.util.Optional;

public class GnaviRestSearchApi extends GnaviApiBase {

    private Optional<String> areaCodeS;
    private Optional<String> freeWord;
    private Optional<String> categoryCodeS;

    private GnaviRestSearchApi(String apiKey, OkHttpClient okHttpClient, Optional<String> areaCodeS, Optional<String> categoryCodeS, Optional<String> freeWord){
        super(apiKey, okHttpClient);
        this.areaCodeS = areaCodeS;
        this.freeWord = freeWord;
        this.categoryCodeS = categoryCodeS;
    }

    @Override
    public String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GnaviRestSearchApiUrl;
    }


    @Override
    public String getUrlFormatJson() {
        if(areaCodeS.isPresent() && freeWord.isPresent() && categoryCodeS.isPresent()){
            return new GnaviApiUrlBuilder
                    .Builder(getBaseUrl(), this.apiKey)
                    .setAreaCodeS(areaCodeS.get())
                    .setCategoryCodeS(categoryCodeS.get())
                    .setFreeWord(freeWord.get())
                    .setFreeWordCondition(Enums.FreeWordCondition.AND.getCondition())
                    .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                    .build()
                    .buildUrl();
        }

        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    public String getUrlFromatXml() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }
}
