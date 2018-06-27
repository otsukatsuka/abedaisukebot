package com.otsukatsuka.daisukebot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otsukatsuka.daisukebot.api.result.CategorySmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GAreaSmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.JsonConverter;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Optional;

@Component
public class GnaviApiClient {

    @Autowired
    ApiConfig apiConfig;

    public GnaviApiClient() { }

    private String getApiKey(){
        String key = apiConfig.getKey();
        return key;
    }

    private GnaviSearchParameters getGnaviSearchParameters(String message){
        return GnaviSearchParameters.create(getApiKey(),"","", "");
    }

    private String getGareaSerachJson(GnaviSearchParameters gnaviSearchParameters){
        return new GAreaSmallSearchApi(gnaviSearchParameters).getUrlFormatJson();
    }
    private String getCategorySerachJson(GnaviSearchParameters gnaviSearchParameters){
        return new CategorySmallSearchApi(gnaviSearchParameters).getUrlFormatJson();
    }
    private String getGnaviRestSearchJson(GnaviSearchParameters gnaviSearchParameters){
        return new GnaviRestSearchApi(gnaviSearchParameters).getUrlFormatJson();
    }

    public GnaviRestSearchResult searchRestaurantByAreaAndCategoryFreeWords(String message) throws IOException {
        GnaviSearchParameters param = getGnaviSearchParameters(message);

        GAreaSmallSearchResult gAreaSmallSearchResult = JsonConverter.deserialize(getGareaSerachJson(param),GAreaSmallSearchResult.class);
        Optional<String> areaCode = gAreaSmallSearchResult.gAreaSmallSearchResult
                .stream()
                .filter(x -> x.areaName.contains("池袋"))
                .map(x -> x.areaName)
                .findFirst();

        CategorySmallSearchResult categorySmallSearchResult = JsonConverter.deserialize(getCategorySerachJson(param), CategorySmallSearchResult.class);
        Optional<String> categoryCode = categorySmallSearchResult.categoryS
                .stream()
                .filter(x -> x.categorySName.contains("居酒屋"))
                .map(x -> x.categorySCode).findFirst();

        param.setAreaSCode(areaCode.get());
        param.setCategorySCode(categoryCode.get());
        param.setFreeWords("");

        return JsonConverter.deserialize(getGnaviRestSearchJson(param), GnaviRestSearchResult.class);
    }
}
