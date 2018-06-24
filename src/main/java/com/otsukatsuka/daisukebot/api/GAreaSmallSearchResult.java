package com.otsukatsuka.daisukebot.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GAreaSmallSearchResult {

    @JsonProperty("@attributes")
    public Map<String, String> attributes;

    @JsonProperty("garea_small")
    public List<GAreaSmallResult> gAreaSmallSearchResult;
}
