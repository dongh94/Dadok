package com.ssafy.dadok.api.response;


import com.ssafy.dadok.db.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserIntroduceResponse {

    private String introduce;

    public static UserIntroduceResponse userIntroduceResponse(User user){
        UserIntroduceResponse userIntroduceResponse = new UserIntroduceResponse();
        userIntroduceResponse.introduce = user.getIntroduce();
        return userIntroduceResponse;
    }
}
