package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.api.result.CategorySmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GAreaSmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import com.otsukatsuka.daisukebot.core.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    private String getGareaSerachJson(GnaviSearchParameters gnaviSearchParameters) throws IOException {
        return new GAreaSmallSearchApi(gnaviSearchParameters).getJson();
    }
    private String getCategorySerachJson(GnaviSearchParameters gnaviSearchParameters) throws IOException {
        return new CategorySmallSearchApi(gnaviSearchParameters).getJson();
    }
    private String getGnaviRestSearchJson(GnaviSearchParameters gnaviSearchParameters) throws IOException {
        return new GnaviRestSearchApi(gnaviSearchParameters).getJson();
    }

    public GnaviRestSearchResult searchRestaurantByAreaAndCategoryFreeWords(String message) throws IOException {
        System.out.println("in searchRestaurantByAreaAndCategoryFreeWords");
        GnaviSearchParameters param = getGnaviSearchParameters(message);

        GAreaSmallSearchResult gAreaSmallSearchResult = JsonConverter.deserialize(getGareaSerachJson(param),GAreaSmallSearchResult.class);
        Optional<String> areaCode = gAreaSmallSearchResult.gAreaSmallSearchResult
                .stream()
                .filter(x -> x.areaName.contains("池袋"))
                .map(x -> x.areaName)
                .findFirst();

        System.out.println("areaCodes : " + areaCode.get());
        CategorySmallSearchResult categorySmallSearchResult = JsonConverter.deserialize(getCategorySerachJson(param), CategorySmallSearchResult.class);
        Optional<String> categoryCode = categorySmallSearchResult.categoryS
                .stream()
                .filter(x -> x.categorySName.contains("居酒屋"))
                .map(x -> x.categorySCode).findFirst();

        System.out.println("categoryCode : " + categoryCode.get());

        param.setAreaSCode(areaCode.get());
        param.setCategorySCode(categoryCode.get());
        param.setFreeWords("");

        return JsonConverter.deserialize(getGnaviRestSearchJson(param), GnaviRestSearchResult.class);
    }
}
