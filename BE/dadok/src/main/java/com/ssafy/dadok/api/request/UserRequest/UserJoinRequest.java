package com.ssafy.dadok.api.request.UserRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*회원가입 용 Dto*/
@Getter
@Setter
@NoArgsConstructor
public class UserJoinRequest {
    private String id;
    private String pw;
    private String name;
    private String birthday;
    private String img_path;
    private String interest;
    private String email;
    private String sex;
    private String introduce;
    private int isCreate = 0;
    private int Auth = 0;
    //여기서만 가능한 관련 로직은 Entity에 넣어두자!
    private int meeting_count; //모임 참여 횟수
    private int meeting_time; //모임 참여 시간
    private int book_count; //읽은책수
}

