package com.otsukatsuka.daisukebot.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.security.InvalidParameterException;

public class JsonConverter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T deserialize(final String json, final Class<T> clazz) throws IOException {

        T object = null;

        if(json == null){
            throw new InvalidParameterException("json is null");
        }

        object = mapper.readValue(json, clazz);

        return object;
    }
}
