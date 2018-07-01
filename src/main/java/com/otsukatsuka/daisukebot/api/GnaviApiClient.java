package com.otsukatsuka.daisukebot.api;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.otsukatsuka.daisukebot.api.result.CategorySmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GAreaSmallSearchResult;
import com.otsukatsuka.daisukebot.api.result.GnaviRestSearchResult;
import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GnaviApiClient {

    @Autowired
    ApiConfig apiConfig;

    public GnaviApiClient() { }

    private String getApiKey(){
        String key = apiConfig.getKey();
        System.out.println(key);
        return key;
    }

    public GnaviSearchParameters getGnaviSearchParameters(String messageExcludeBotName){
        GnaviSearchParameters parameters = GnaviSearchParameters.create(getApiKey(),"","", "");

        StringBuilder sb = new StringBuilder(messageExcludeBotName);

        Integer index = 0;
        String place = "";

        for (String con : Consts.bot.conjunction){
            int i = sb.indexOf(con);
            if(i != -1){
                place = sb.substring(0, i);
                index = i;
                break;
            }
        }
        sb.delete(0, index + 1);
        index = 0;

        parameters.setAreaText(place);
        System.out.println("place : " + place + "message " + sb.toString());

        String freeWord = "";
        for (String con : Consts.bot.conjunction){
            int i = sb.indexOf(con);
            if(i != -1){
                freeWord = sb.substring(0, i);
                index = i;
                break;
            }
        }
        parameters.setFreeWords(freeWord);
        System.out.println("freeWord : " + freeWord + "message " + sb.toString());
        if(!freeWord.isEmpty())
            sb.delete(0, index + 1);

        Consts.bot.beg.forEach(x -> {
            int indexof = sb.indexOf(x);
            if(indexof != -1)
                sb.delete(sb.indexOf(x), sb.length());
        });

        String category = sb.toString();
        parameters.setCategoryText(category);
        System.out.println("category : " + category);

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

    private GnaviRestSearchResult searchRestaurantByAreaAndCategoryFreeWords(String message) throws IOException {
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

    public List<Message> GnaviRestList(String message) throws IOException{
        GnaviRestSearchResult gnaviRestSearchResult = searchRestaurantByAreaAndCategoryFreeWords(message);

        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage("message : " + message + "\n"
                + "場所 " + gnaviRestSearchResult.parameters.getAreaText() + "\n"
                + "フリーワード" + gnaviRestSearchResult.parameters.getFreeWords() + "\n"
                + "カテゴリ" + gnaviRestSearchResult.parameters.getCategoryText()));

        gnaviRestSearchResult.rest.forEach(x -> {
            messages.add(new TextMessage(x.name  + "\n"
                    + x.address + "\n"
                    + "定休日 " + x.holiday  + "\n"
                    + "営業時間" + x.opentime + "\n"
                    + x.url));
        });

        return messages;
    }
}
