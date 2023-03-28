package com.ssafy.dadok.api.controller;


import com.ssafy.dadok.api.response.BookResponseDto;
import com.ssafy.dadok.api.response.InterparkResponseDto;
import com.ssafy.dadok.api.service.BookApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class BookApiController {

    private final BookApiService bookApiService;

    @GetMapping("/{keyword}")
    public BookResponseDto Book(@PathVariable("keyword") String keyword) throws Exception{
        return bookApiService.requestBook(keyword);
    }
    @GetMapping("isbn/{keyword}")
    public BookResponseDto Isbn(@PathVariable("keyword") String keyword) throws Exception{
        return bookApiService.requestIsbn(keyword);
    }
    @GetMapping("/popular")
    public List<InterparkResponseDto> popular() throws Exception{
        return bookApiService.getPopluarBooks();
    }



}
