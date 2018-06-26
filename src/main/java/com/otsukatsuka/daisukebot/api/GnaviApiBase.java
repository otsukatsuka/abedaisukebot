package com.otsukatsuka.daisukebot.api;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Optional;

public class GnaviApiBase implements GnaviApiInterface{

    protected final String apiKey;
    protected final OkHttpClient httpClient;

    protected GnaviApiBase(String apiKey, OkHttpClient httpClient){
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    protected String getResult(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = httpClient.newCall(request);
        Response response = call.execute();
        String result = response.body().string();

        return result;
    }

    public <T extends GnaviApiBase> T getInstance(String apiKey, OkHttpClient httpClient, Optional<String> areaCodeS, Optional<String> categoryCodeS, Optional<String> freeWord){
        return (T) new GnaviApiBase(apiKey, httpClient);
    }

    @Override
    public String getBaseUrl() {
        throw new NotImplementedException();
    }

    @Override
    public String getUrlFormatJson() {
        throw new NotImplementedException();
    }

    @Override
    public String getUrlFromatXml() {
        throw new NotImplementedException();
    }

    public String getJson() throws IOException {
        return getResult(getUrlFormatJson());
    }

    public String getXml() throws IOException {
        return getResult(getUrlFromatXml());
    }
}
