package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.Consts;
import com.otsukatsuka.daisukebot.Enums.GnaviApiFormatType;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GAreaSmallSearchApi {

    private final String apiKey;
    private final OkHttpClient httpClient;

    private GAreaSmallSearchApi(String apiKey, OkHttpClient httpClient){
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    public static GAreaSmallSearchApi getInstance(String apiKey, OkHttpClient httpClient){
        return new GAreaSmallSearchApi(apiKey, httpClient);
    }

    public String getUrlFormatJson(){
        return new GnaviApiUrlBuilder
                .Builder(Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl, this.apiKey)
                .setFormatType(GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    public String getUrlFromatXml(){
        return new GnaviApiUrlBuilder
                .Builder(Consts.Api.GnaviApi.Url.GAreaSmallSearchApiUrl, this.apiKey)
                .setFormatType(GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    public String getJson() throws IOException {
        return getResult(getUrlFormatJson());
    }

    public String getXml() throws IOException {
        return getResult(getUrlFromatXml());
    }

    private String getResult(String url) throws IOException {
        System.out.println("url: " + url);

        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        String result = response.body().string();

        System.out.println("result: " + result.substring(0,10));

        return result;
    }
}
