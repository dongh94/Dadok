package com.ssafy.dadok.api.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;

@Data
@NoArgsConstructor
public class InterparkResponseDto {

    private String title;
    private String coverSmallUrl;
    private String author;
    private String isbn;

    public static InterparkResponseDto interparkResponseDto(JSONObject jsonObject){
        InterparkResponseDto interparkResponseDto = new InterparkResponseDto();
        interparkResponseDto.title = jsonObject.get("title").toString();
        interparkResponseDto.coverSmallUrl = jsonObject.get("coverSmallUrl").toString();
        interparkResponseDto.author = jsonObject.get("author").toString();
        interparkResponseDto.isbn = jsonObject.get("isbn").toString();

        return interparkResponseDto;

    }


}
