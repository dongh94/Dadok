package com.ssafy.dadok.api.service;


import com.ssafy.dadok.api.response.BookResponseDto;
import com.ssafy.dadok.api.response.InterparkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class BookApiService {

    private final RestTemplate restTemplate;

    private final String CLIENT_ID = "";
    private final String CLIENT_SECRET="";

    private final String OpenNaverBookUrl_getBooks = "https://openapi.naver.com/v1/search/book_adv?d_titl={keyword}";
    private final String OpenNaverBookUrl_getIsbn =  "https://openapi.naver.com/v1/search/book_adv?d_isbn={keyword}";

    //이름으로 도서 검색
    public BookResponseDto requestBook(String keyword){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret",CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(OpenNaverBookUrl_getBooks, HttpMethod.GET, entity, BookResponseDto.class, keyword).getBody();
    }

    //isbn으로 도서 검색
    public BookResponseDto requestIsbn(String keyword){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret",CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(OpenNaverBookUrl_getIsbn, HttpMethod.GET, entity, BookResponseDto.class, keyword).getBody();
    }


    private final InterparkClient interparkClient;

    public List<InterparkResponseDto> getPopluarBooks() {
        return interparkClient.getPopularBooks();
    }

}
