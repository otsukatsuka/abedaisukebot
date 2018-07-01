package com.otsukatsuka.daisukebot.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.otsukatsuka.daisukebot.api.GnaviSearchParameters;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GnaviRestSearchResult {
    @JsonProperty("@attributes")
    public Map<String, String> attributes;

    @JsonProperty("rest")
    public List<GnaviRestResult> rest;

    public GnaviSearchParameters parameters;
}
