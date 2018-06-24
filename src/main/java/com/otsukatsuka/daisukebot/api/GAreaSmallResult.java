package com.otsukatsuka.daisukebot.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GAreaSmallResult {

    @JsonProperty("areacode_s")
    public String areaCode;

    @JsonProperty("areaname_s")
    public String areaName;

    @JsonProperty("garea_middle")
    public Map<String, String> gareaMiddle;

    @JsonProperty("garea_large")
    public Map<String, String> gareaLarge;

    @JsonProperty("pref")
    public Map<String, String> pref;
}
