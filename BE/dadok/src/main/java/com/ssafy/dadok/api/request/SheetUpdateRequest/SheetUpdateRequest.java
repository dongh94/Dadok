package com.ssafy.dadok.api.request.SheetUpdateRequest;

import com.ssafy.dadok.db.entity.BookMeeting;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SheetUpdateRequest {

    private String hostId;
    private String isbn; //진행한 도서 isbn
    private String date;//진행 일시
    private String topic; //발제

    private int people; //참여한 멤버수

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

}
