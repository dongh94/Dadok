package com.ssafy.dadok.db.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Long id;

    private Long sheet_id;
    private String isbn;
    private String date;

    private String meetName;
    private String hostId;
    private String topic;
    private int people;

    private String member1;
    private String member2;
    private String member3;
    private String member4;
    private String member5;
    private String member6;

    private String opinion1;
    private String opinion2;
    private String opinion3;
    private String opinion4;
    private String opinion5;
    private String opinion6;

    private String img_path;

    public static Book createBook(Sheet sheet){
        Book book = new Book();
        book.isbn = sheet.getIsbn();;
        book.date = sheet.getDate();
        book.hostId = sheet.getHostId();
        book.topic  = sheet.getTopic();
        book.people = sheet.getPeople();
        book.meetName = sheet.getMeetName();
        book.member1 = sheet.getMember1();
        book.member2 = sheet.getMember2();
        book.member3 = sheet.getMember3();
        book.member4 = sheet.getMember4();
        book.member5 = sheet.getMember5();
        book.member6 = sheet.getMember6();

        book.opinion1 = sheet.getOpinion1();
        book.opinion2 = sheet.getOpinion2();
        book.opinion3 = sheet.getOpinion3();
        book.opinion4 = sheet.getOpinion4();
        book.opinion5 = sheet.getOpinion5();
        book.opinion6 = sheet.getOpinion6();
        book.sheet_id = sheet.getId();
        book.img_path = sheet.getImg_path();

        return book;

    }
}
