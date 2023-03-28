package com.ssafy.dadok.api.response;

import com.ssafy.dadok.db.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*User정보 조회용  Dto*/
@Getter
@NoArgsConstructor
public class LoginUserResponse {


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
    private String introduce;


    public static LoginUserResponse CreateLoginUser(User user) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.id = user.getId();
        loginUserResponse.pw = user.getPw();
        loginUserResponse.name = user.getName();
        loginUserResponse.birthday = user.getBirthday();
        loginUserResponse.interest = user.getInterest();
        loginUserResponse.email = user.getEmail();
        loginUserResponse.img_path = user.getImg_path();
        loginUserResponse.sex = user.getSex();
        loginUserResponse.meeting_count = user.getMeeting_count();
        loginUserResponse.meeting_time = user.getMeeting_time();
        loginUserResponse.book_count = user.getBook_count();
        loginUserResponse.isCreate = user.getIsCreate();
        loginUserResponse.emailAuth = user.getAuth();
        loginUserResponse.introduce = user.getIntroduce();

        return loginUserResponse;
    }
}
