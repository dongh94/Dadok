package com.ssafy.dadok.api.request.UserRequest;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class
LoginUserRequest {
    private String id;
    private String pw;
    private String name;
    private String birthday;
    private String interest;
    private String email;
    private String img_path;
    private String sex;
    private int meeting_count; //모임 참여 횟수
    private int meeting_time; //모임 참여 시간
    private int book_count; //읽은책수
    private int isCreate;
    private int emailAuth;
}
