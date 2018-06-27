package com.otsukatsuka.daisukebot.api;

import okhttp3.OkHttpClient;

public class GnaviSearchParameters {

    private String areaSCode;

    private String categorySCode;

    private String freeWords;

    private String apiKey;

    private OkHttpClient httpClient;

    private GnaviSearchParameters(String apiKey, String areaSCode, String categorySCode, String freeWords){
        this.apiKey = apiKey;
        this.areaSCode = areaSCode;
        this.categorySCode = categorySCode;
        this.freeWords = freeWords;
        this.httpClient = new OkHttpClient();
    }

    public static GnaviSearchParameters create(String apiKey, String areaSCode, String categorySCode, String freeWords){
        return new GnaviSearchParameters(apiKey, areaSCode, categorySCode, freeWords);
    }

    public String getApiKey(){
        return apiKey;
    }

    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }

    public String getAreaSCode() {
        return areaSCode;
    }

    public void setAreaSCode(String areaSCode){
        this.areaSCode = areaSCode;
    }

    public String getCategorySCode() {
        return categorySCode;
    }

    public void setCategorySCode(String categorySCode){
        this.categorySCode = categorySCode;
    }

    public String getFreeWords() {
        return freeWords;
    }

    public void setFreeWords(String freeWords){
        this.freeWords = freeWords;
    }

    public OkHttpClient getHttpClient(){
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient){
        this.httpClient = httpClient;
    }

}
