package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Enums;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public abstract class AbstractGnaviApi {

    protected abstract String getBaseUrl();

    protected final String apiKey;
    protected final OkHttpClient httpClient;

    protected AbstractGnaviApi(String apiKey, OkHttpClient httpClient){
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

    protected abstract String getUrlFormatJson();

    protected abstract String getUrlFromatXml();

    public String getJson() throws IOException {
        return getResult(getUrlFormatJson());
    }

    public String getXml() throws IOException {
        return getResult(getUrlFromatXml());
    }
}
