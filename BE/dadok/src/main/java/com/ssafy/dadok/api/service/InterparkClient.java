package com.ssafy.dadok.api.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.dadok.api.response.InterparkResponseDto;
import com.ssafy.dadok.common.config.InterparkProperties;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class InterparkClient {
    private static final int CATEGORY = 100;
    private final InterparkProperties properties;
    private final WebClient webClient;
    private ObjectMapper objectMapper = new ObjectMapper();



    public List<InterparkResponseDto> getPopularBooks() {
        List<InterparkResponseDto> interparkResponseDto = convertToResponse(getBooksFromApi());
        return interparkResponseDto;
    }

    private String getBooksFromApi() {
        String items = null;
        try {

            items = webClient.get()
                    .uri(builder -> builder.path("/bestSeller.api")
                            .queryParam("categoryId", CATEGORY)
                            .queryParam("output", "json")
                            .queryParam("key", properties.getKey()).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class).block();
        } catch (Exception e) {
        }
        return items;
    }

    private List<InterparkResponseDto> convertToResponse(String textData) {
         List<InterparkResponseDto> interparkResponseDtos = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(textData);
            JSONArray itemArray = (JSONArray) jsonObject.get("item");

            for (int i = 0; i < 10 ; i++) {
                JSONObject itemObject = (JSONObject) itemArray.get(i);
                interparkResponseDtos.add(InterparkResponseDto.interparkResponseDto(itemObject));
            }

        } catch (Exception e) {
        }
        return interparkResponseDtos;
    }
}
