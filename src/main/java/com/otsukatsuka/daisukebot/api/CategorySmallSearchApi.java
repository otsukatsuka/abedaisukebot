package com.otsukatsuka.daisukebot.api;

import com.otsukatsuka.daisukebot.core.Consts;
import com.otsukatsuka.daisukebot.core.Enums;

public class CategorySmallSearchApi extends AbstractGnaviApi {

    public CategorySmallSearchApi(GnaviSearchParameters gnaviSearchParameters) {
        super(gnaviSearchParameters);
    }

    @Override
    public String getBaseUrl() {
        return Consts.Api.GnaviApi.Url.CategorySmallSearchUrl;
    }

    @Override
    public String getUrlFormatJson() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), gnaviSearchParameters.getApiKey())
                .setFormatType(Enums.GnaviApiFormatType.Json.getFormatType())
                .build()
                .buildUrl();
    }

    @Override
    public String getUrlFromatXml() {
        return new GnaviApiUrlBuilder
                .Builder(getBaseUrl(), gnaviSearchParameters.getApiKey())
                .setFormatType(Enums.GnaviApiFormatType.Xml.getFormatType())
                .build()
                .buildUrl();
    }
}
