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

    private OkHttpClient getHttpClient(){
        return new OkHttpClient();
    }

    private <T extends GnaviApiBase> String getJson(Class<T> clazz, Optional<GnaviSearchParameters> gnaviSearchParameters)
            throws IOException, IllegalAccessException, InstantiationException
    {
        if(gnaviSearchParameters.isPresent()){
            GnaviSearchParameters param = gnaviSearchParameters.get();

            return clazz.newInstance().getInstance(getApiKey(),
                    getHttpClient(),
                    Optional.ofNullable(param.getAreaSCode()),
                    Optional.ofNullable(param.getCategorySCode()),
                    Optional.ofNullable(param.getFreeWords())).getJson();
        }

        return clazz.newInstance().getInstance(getApiKey(), getHttpClient(), null, null, null).getJson();
    }

    private GnaviSearchParameters createParam(String message){
        return GnaviSearchParameters.create("","","");
    }

    public GnaviRestSearchResult searchRestaurantByAreaAndCategoryFreeWords(String message) throws IllegalAccessException, IOException, InstantiationException {
        GnaviSearchParameters param = createParam(message);

        GAreaSmallSearchResult gAreaSmallSearchResult = JsonConverter.deserialize(getJson(GAreaSmallSearchApi.class, null),GAreaSmallSearchResult.class);
        Optional<String> areaCode = gAreaSmallSearchResult.gAreaSmallSearchResult
                .stream()
                .filter(x -> x.areaName.contains("池袋"))
                .map(x -> x.areaName)
                .findFirst();

        CategorySmallSearchResult categorySmallSearchResult = JsonConverter.deserialize(getJson(CategorySmallSearchApi.class, null), CategorySmallSearchResult.class);
        Optional<String> categoryCode = categorySmallSearchResult.categoryS
                .stream()
                .filter(x -> x.categorySName.contains("居酒屋"))
                .map(x -> x.categorySCode).findFirst();

        GnaviSearchParameters searchParameters = GnaviSearchParameters.create(areaCode.get(), categoryCode.get(), "");

        return JsonConverter.deserialize(getJson(GnaviRestSearchApi.class, Optional.ofNullable(searchParameters)) , GnaviRestSearchResult.class);
    }
}
