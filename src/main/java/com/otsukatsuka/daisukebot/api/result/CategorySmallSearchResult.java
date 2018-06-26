package com.otsukatsuka.daisukebot.api.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategorySmallSearchResult {

    @JsonProperty("category_s")
    public List<CategorySmallResult> categoryS;
}
