package com.otsukatsuka.daisukebot.api;

public class GnaviSearchParameters {

    private final String areaSCode;

    private final String categorySCode;

    private final String freeWords;

    private GnaviSearchParameters(String areaSCode, String categorySCode, String freeWords){
        this.areaSCode = areaSCode;
        this.categorySCode = categorySCode;
        this.freeWords = freeWords;
    }

    public static GnaviSearchParameters create(String areaSCode, String categorySCode, String freeWords){
        return new GnaviSearchParameters(areaSCode, categorySCode, freeWords);
    }

    public String getAreaSCode() {
        return areaSCode;
    }

    public String getCategorySCode() {
        return categorySCode;
    }

    public String getFreeWords() {
        return freeWords;
    }
}
