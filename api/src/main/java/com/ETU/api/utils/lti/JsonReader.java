package com.ETU.api.utils.lti;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Ilya Tsygankov
 * @created 20.05.2024
 */
public class JsonReader {

    public static <T> T readJsonFromUrl(String url, Class<T> claz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try (InputStream is = new URL(url).openStream()) {
            return mapper.readValue(is, claz);
        }
    }

}