package com.ssafy.dadok.api.response;

import com.ssafy.dadok.db.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyMeetMemberResponse {
    private String id;
    private String name;

    public static MyMeetMemberResponse CreateMyMeetMemberResponse(User member){
        MyMeetMemberResponse myMeetMemberResponse = new MyMeetMemberResponse();
        myMeetMemberResponse.id = member.getId();
        myMeetMemberResponse.name = member.getName();
        return myMeetMemberResponse;
    }
}
