package com.ssafy.dadok.api.response;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckDuplicateResponse {
    String id;

    public static CheckDuplicateResponse createResponse(String id){
        CheckDuplicateResponse checkDuplicateResponse = new CheckDuplicateResponse();
        checkDuplicateResponse.id = id;
        return checkDuplicateResponse;
    }
}
