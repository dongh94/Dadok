package com.ssafy.dadok.db.entity;


import com.ssafy.dadok.api.request.SheetUpdateRequest.SheetUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
public class Sheet {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sheet_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmeeting_id")
    private BookMeeting bookMeeting;

    private String isbn; //진행한 도서 isbn
    private String date;//진행 일시


    private String meetName; //모임명
    private String hostId; //주최자 Id
    private String topic; //발제

    private int people; //가입된 멤버수

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

    public void updateIsbn(String isbn){
        this.isbn = isbn;
    }
    public void updateImgPath(String img_path){
        this.img_path = img_path;
    }

    public static Sheet createSheet(BookMeeting bookMeeting){
        Sheet sheet = new Sheet();
        sheet.bookMeeting = bookMeeting;
        sheet.topic = "발제를 입력해주세요";
        sheet.isbn ="도서 검색을 통해 isbn을 입력해주세요";
        sheet.date = bookMeeting.getStartDay();
        sheet.meetName = bookMeeting.getName();
        sheet.hostId = bookMeeting.getHost().getId();
        sheet.people = bookMeeting.getCapacity();
        sheet.member1="참가자1";
        sheet.member2="참가자2";
        sheet.member3="참가자3";
        sheet.member4="참가자4";
        sheet.member5="참가자5";
        sheet.member6="참가자6";
        sheet.opinion1="의견을 입력해주세요";
        sheet.opinion2="의견을 입력해주세요.";
        sheet.opinion3="의견을 입력해주세요.";
        sheet.opinion4="의견을 입력해주세요.";
        sheet.opinion5="의견을 입력해주세요.";
        sheet.opinion6="의견을 입력해주세요.";
        sheet.img_path=null;

        return sheet;
    }

    public void updateSheet(SheetUpdateRequest sheetUpdateRequest){
        this.isbn = sheetUpdateRequest.getIsbn();
        this.date = sheetUpdateRequest.getDate();
        this.topic = sheetUpdateRequest.getTopic();
        this.people = sheetUpdateRequest.getPeople();

        this.member1 = sheetUpdateRequest.getMember1();
        this.member2 = sheetUpdateRequest.getMember2();
        this.member3 = sheetUpdateRequest.getMember3();
        this.member4 = sheetUpdateRequest.getMember4();
        this.member5 = sheetUpdateRequest.getMember5();
        this.member6 = sheetUpdateRequest.getMember6();

        this.opinion1 = sheetUpdateRequest.getOpinion1();
        this.opinion2 = sheetUpdateRequest.getOpinion2();
        this.opinion3 = sheetUpdateRequest.getOpinion3();
        this.opinion4 = sheetUpdateRequest.getOpinion4();
        this.opinion5 = sheetUpdateRequest.getOpinion5();
        this.opinion6 = sheetUpdateRequest.getOpinion6();

    }

}
