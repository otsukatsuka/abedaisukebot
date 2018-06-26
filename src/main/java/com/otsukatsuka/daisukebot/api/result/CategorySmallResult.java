package com.otsukatsuka.daisukebot.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategorySmallResult {

    @JsonProperty("category_s_code")
    public String categorySCode;

    @JsonProperty("category_s_name")
    public String categorySName;

    @JsonProperty("category_l_code")
    public String categoryICode;
}
