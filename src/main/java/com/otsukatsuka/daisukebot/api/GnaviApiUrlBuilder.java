package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Enums.GnaviApiParam;
import com.otsukatsuka.daisukebot.core.Enums.GnaviApiFormatType;
import com.otsukatsuka.daisukebot.core.Enums.FreeWordCondition;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

/*
 * ぐるなびApiUrl組み立てクラス
 */
public class GnaviApiUrlBuilder {

    private final Map<GnaviApiParam, Object> parameters;
    private final String baseUrl;

    private GnaviApiUrlBuilder(Builder builder){
        this.parameters = builder.parameters;
        this.baseUrl = builder.baseUrl;
    }
    public static class Builder{
        private final Map<GnaviApiParam, Object> parameters = new EnumMap<>(GnaviApiParam.class);
        private final String baseUrl;

        public Builder(String baseUrl, String apiKey){
            this.baseUrl = baseUrl;
            set(GnaviApiParam.APIKEY, apiKey);
        }

        private void set(GnaviApiParam key, Object value){
            parameters.put(key, value);
        }

        public Builder setFormatType(String formatType){
            // formatTypeが有効なものか確認
            Optional<GnaviApiFormatType> any = EnumSet.allOf(GnaviApiFormatType.class)
                    .stream()
                    .filter(x -> x.getFormatType().equals(formatType))
                    .findAny();

            if(!any.isPresent())
                return this;

            set(GnaviApiParam.Format, formatType);

            return this;
        }

        public Builder setAreaCodeS(String areaCodeS){
            set(GnaviApiParam.AreaCodeS, areaCodeS);
            return this;
        }

        public Builder setCategoryCodeS(String categoryCodeS){
            set(GnaviApiParam.CategoryCodeS, categoryCodeS);
            return this;
        }

        public Builder setFreeWord(String freeWord){
            set(GnaviApiParam.FreeWord, freeWord);
            return this;
        }

        public Builder setFreeWordCondition(int freeWordCondition){
            // Conditionが有効なものか確認
            Optional<FreeWordCondition> any = EnumSet.allOf(FreeWordCondition.class)
                    .stream()
                    .filter(x -> x.getCondition() == freeWordCondition)
                    .findAny();

            if(!any.isPresent())
                return this;

            set(GnaviApiParam.FreewordCondition, freeWordCondition);
            return this;
        }

        public GnaviApiUrlBuilder build(){
            if(!parameters.containsKey(GnaviApiParam.APIKEY) || parameters.get(GnaviApiParam.APIKEY).equals("")){
                throw new NullPointerException();
            }

            return new GnaviApiUrlBuilder(this);
        }
    }

    public String buildUrl(){
        String param = "";

        for(Map.Entry<GnaviApiParam, Object> entry : parameters.entrySet()){
            param += entry.getKey().getParam() + "=" + entry.getValue() + "&";
        }

        if(param.length() != 0)
            param = param.substring(0, param.length()-1);

        System.out.println("url : " + this.baseUrl + param);

        return this.baseUrl + param;
    }
}
