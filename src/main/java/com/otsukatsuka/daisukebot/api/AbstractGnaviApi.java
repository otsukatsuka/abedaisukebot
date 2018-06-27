package com.otsukatsuka.daisukebot.api;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public abstract class AbstractGnaviApi{

    protected final GnaviSearchParameters gnaviSearchParameters;

    protected AbstractGnaviApi(GnaviSearchParameters gnaviSearchParameters){
        this.gnaviSearchParameters = gnaviSearchParameters;
    }

    protected String getResult(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Call call = gnaviSearchParameters.getHttpClient().newCall(request);
        Response response = call.execute();
        String result = response.body().string();

        return result;
    }

    protected abstract String getBaseUrl();

    protected abstract String getUrlFormatJson();

    protected abstract String getUrlFromatXml();

    public String getJson() throws IOException {
        return getResult(getUrlFormatJson());
    }

    public String getXml() throws IOException {
        return getResult(getUrlFromatXml());
    }
}
