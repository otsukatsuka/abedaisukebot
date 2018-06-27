package com.otsukatsuka.daisukebot.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GnaviRestResult {

    @JsonProperty("name")
    public String name;

    @JsonProperty("address")
    public String address;

    @JsonProperty("holiday")
    public String holiday;

    @JsonProperty("opentime")
    public String opentime;

    @JsonProperty("url")
    public String url;
}
