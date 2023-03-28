package com.ssafy.dadok.api.response;

import com.ssafy.dadok.db.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class MyBookResponse {

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
    private Long sheet_Id;
    private String img_path;

    public static MyBookResponse CreateResponse(Book book){
        MyBookResponse myBookResponse = new MyBookResponse();
        myBookResponse.isbn = book.getIsbn();
        myBookResponse.date = book.getDate();
        myBookResponse.meetName = book.getMeetName();
        myBookResponse.hostId = book.getHostId();
        myBookResponse.topic = book.getTopic();

        myBookResponse.people = book.getPeople();
        myBookResponse.member1 = book.getMember1();
        myBookResponse.member2 = book.getMember2();
        myBookResponse.member3 = book.getMember3();
        myBookResponse.member4 = book.getMember4();
        myBookResponse.member5 = book.getMember5();
        myBookResponse.member6 = book.getMember6();

        myBookResponse.opinion1 = book.getOpinion1();
        myBookResponse.opinion2 = book.getOpinion2();
        myBookResponse.opinion3 = book.getOpinion3();
        myBookResponse.opinion4 = book.getOpinion4();
        myBookResponse.opinion5 = book.getOpinion5();
        myBookResponse.opinion6 = book.getOpinion6();
        myBookResponse.sheet_Id = book.getSheet_id();
        myBookResponse.img_path = book.getImg_path();

        return myBookResponse;


    }
}
