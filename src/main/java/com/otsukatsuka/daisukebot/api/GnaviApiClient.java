package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.api.result.CategorySmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GAreaSmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import com.otsukatsuka.daisukebot.core.Consts;
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

    public GnaviSearchParameters getGnaviSearchParameters(String messageExcludeBotName){
        GnaviSearchParameters parameters = GnaviSearchParameters.create(getApiKey(),"","", "");

        StringBuilder sb = new StringBuilder(messageExcludeBotName);

        Integer index = 1;
        String place = "";

        for (String con : Consts.bot.conjunction){
            index++;
            int i = sb.indexOf(con);
            if(i != -1){
                place = sb.substring(0, i);
                break;
            }
        }
        sb.delete(0, index + 1);
        index = 1;

        parameters.setAreaText(place);

        String freeWord = "";
        for (String con : Consts.bot.conjunction){
            index++;
            int i = sb.indexOf(con);
            if(i != -1){
                freeWord = sb.substring(0, i);
                break;
            }
        }
        sb.delete(0, index + 1);
        parameters.setFreeWords(freeWord);

        Consts.bot.beg.forEach(x -> {
            int indexof = sb.indexOf(x);
            if(indexof != -1)
                sb.delete(sb.indexOf(x), sb.length());
        });

        String category = sb.toString();
        parameters.setCategoryText(category);

        return parameters;
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
                .filter(x -> x.areaName.contains(param.getAreaText()))
                .map(x -> x.areaCode)
                .findFirst();

        System.out.println("areaCode : " + areaCode.get());
        CategorySmallSearchResult categorySmallSearchResult = JsonConverter.deserialize(getCategorySerachJson(param), CategorySmallSearchResult.class);
        Optional<String> categoryCode = categorySmallSearchResult.categoryS
                .stream()
                .filter(x -> x.categorySName.contains(param.getCategoryText()))
                .map(x -> x.categorySCode).findFirst();

        System.out.println("categoryCode : " + categoryCode.get());

        param.setAreaSCode(areaCode.get());
        param.setCategorySCode(categoryCode.get());

        GnaviRestSearchResult result = JsonConverter.deserialize(getGnaviRestSearchJson(param), GnaviRestSearchResult.class);
        result.parameters = param;
        return result;
    }
}
