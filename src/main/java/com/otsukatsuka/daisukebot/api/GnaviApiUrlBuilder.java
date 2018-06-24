package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.Consts;
import com.otsukatsuka.daisukebot.Enums.GnaviApiParam;
import com.otsukatsuka.daisukebot.Enums.GnaviApiFormatType;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;

/*
 * ぐるなびApiUrl組み立てクラス
 */
public class GnaviApiUrlBuilder {

    private final Map<GnaviApiParam, String> parameters;

    private GnaviApiUrlBuilder(Builder builder){
        this.parameters = builder.parameters;
    }
    public static class Builder{
        private final Map<GnaviApiParam, String> parameters = new EnumMap<>(GnaviApiParam.class);

        public Builder(String apiKey){
            set(GnaviApiParam.APIKEY, apiKey);
        }

        private void set(GnaviApiParam key, String value){
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

            parameters.put(GnaviApiParam.Format, formatType);
            return this;
        }

        public GnaviApiUrlBuilder build(){

            if(!parameters.containsKey(GnaviApiParam.APIKEY) || !parameters.get(GnaviApiParam.APIKEY).equals("")){
                throw new NullPointerException();
            }

            return new GnaviApiUrlBuilder(this);
        }
    }
}