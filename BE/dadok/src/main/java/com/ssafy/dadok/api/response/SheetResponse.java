package com.ssafy.dadok.api.response;


import com.ssafy.dadok.db.entity.Sheet;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SheetResponse {


    private String isbn; //진행한 도서 isbn
    private String date;//진행 일시
    private String meetName; //모임명
    private String hostId; //주최자 Id
    private String topic; //발제

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

    public static SheetResponse createResponse(Sheet sheet){

        SheetResponse sheetResponse = new SheetResponse();

        sheetResponse.isbn = sheet.getIsbn();
        sheetResponse.date = sheet.getDate();
        sheetResponse.meetName = sheet.getMeetName();
        sheetResponse.hostId = sheet.getHostId();
        sheetResponse.topic = sheet.getTopic();

        sheetResponse.member1 = sheet.getMember1();
        sheetResponse.member2 = sheet.getMember2();
        sheetResponse.member3 = sheet.getMember3();
        sheetResponse.member4 = sheet.getMember4();
        sheetResponse.member5 = sheet.getMember5();
        sheetResponse.member6 = sheet.getMember6();

        sheetResponse.opinion1 = sheet.getOpinion1();
        sheetResponse.opinion2 = sheet.getOpinion2();
        sheetResponse.opinion3 = sheet.getOpinion3();
        sheetResponse.opinion4 = sheet.getOpinion4();
        sheetResponse.opinion5 = sheet.getOpinion5();
        sheetResponse.opinion6 = sheet.getOpinion6();
        sheetResponse.img_path = sheet.getImg_path();

        return sheetResponse;
    }

}
