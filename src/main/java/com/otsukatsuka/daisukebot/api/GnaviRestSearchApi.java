package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.OkHttpClient;

import java.util.Optional;

public class GnaviRestSearchApi extends AbstractGnaviApi{

    private Optional<String> areaCodeS;
    private Optional<String> freeWord;

    private GnaviRestSearchApi(String apiKey, OkHttpClient okHttpClient, Optional<String> areaCodeS, Optional<String> freeWord){
        super(apiKey, okHttpClient);
        this.areaCodeS = areaCodeS;
        this.freeWord = freeWord;
    }

    @Override
    protected String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.GnaviRestSearchApiUrl;
    }


    @Override
    protected String getUrlFormatJson() {
        if(areaCodeS.isPresent() && freeWord.isPresent()){
            return new GnaviApiUrlBuilder
                    .Builder(getBaseUrl(), this.apiKey)
                    .setAreaCodeS(areaCodeS.get())
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
    protected String getUrlFromatXml() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), this.apiKey)
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }

    public GnaviRestSearchApi getInstance(String apiKey, OkHttpClient okHttpClient, Optional<String> areaCodeS, Optional<String> freeWord){
        return new GnaviRestSearchApi(apiKey, okHttpClient, areaCodeS, freeWord);
    }
}
