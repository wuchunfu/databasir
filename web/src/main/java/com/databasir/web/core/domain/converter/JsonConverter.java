package com.databasir.web.core.domain.converter;

import com.databasir.web.core.domain.data.SchemaMetaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
public class JsonConverter {

    @Autowired
    private ObjectMapper objectMapper;

    public JSON toJson(List<String> array) {
        try {
            String json = objectMapper.writeValueAsString(array);
            return JSON.valueOf(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<String> fromJson(JSON json) {
        String data = json.data();
        if (data == null) {
            return Collections.emptyList();
        } else {
            try {
                return objectMapper.readValue(data.getBytes(StandardCharsets.UTF_8),
                        new TypeReference<List<String>>() {
                        });
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public JSON toJson(SchemaMetaResponse response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            return JSON.valueOf(json);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public SchemaMetaResponse of(JSON json) {
        try {
            return objectMapper.readValue(json.data().getBytes(StandardCharsets.UTF_8), SchemaMetaResponse.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
