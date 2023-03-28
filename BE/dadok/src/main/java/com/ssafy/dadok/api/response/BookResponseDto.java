package com.ssafy.dadok.api.response;


import lombok.Data;

@Data
public class BookResponseDto {
    private int display;
    private Item[] items;

    @Data
    static class Item{
        private String title;
        private String author;
        private String isbn;
        private String pubdate;
        private String image;
        private String publisher;
    }

}
