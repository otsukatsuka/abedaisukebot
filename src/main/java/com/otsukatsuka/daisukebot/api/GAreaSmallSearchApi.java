package com.otsukatsuka.daisukebot.api;

public class GAreaSmallSearchApi {

    private final String apiKey;

    private GAreaSmallSearchApi(String apiKey){
        this.apiKey = apiKey;
    }

    public static GAreaSmallSearchApi getInstance(String apiKey){
        return new GAreaSmallSearchApi(apiKey);
    }


}
