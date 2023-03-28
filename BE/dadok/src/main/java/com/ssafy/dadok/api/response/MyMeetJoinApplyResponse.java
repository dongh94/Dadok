package com.ssafy.dadok.api.response;


import com.ssafy.dadok.db.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MyMeetJoinApplyResponse {
    private String applicantId;
    private String applicantName;
    private String applicantBirthday;
    private String applicantInterest;
    private String profileImgPath;
    private String applicantIntroduce;

    private int applicantReadBooks;

    public static MyMeetJoinApplyResponse createResponse(User applicant) {
        MyMeetJoinApplyResponse myMeetJoinApplyResponse = new MyMeetJoinApplyResponse();
        myMeetJoinApplyResponse.applicantId = applicant.getId();
        myMeetJoinApplyResponse.applicantName = applicant.getName();
        myMeetJoinApplyResponse.applicantBirthday = applicant.getBirthday();
        myMeetJoinApplyResponse.applicantInterest = applicant.getInterest();
        myMeetJoinApplyResponse.profileImgPath = applicant.getImg_path();
        myMeetJoinApplyResponse.applicantReadBooks = applicant.getBook_count();
        myMeetJoinApplyResponse.applicantIntroduce = applicant.getIntroduce();
        return myMeetJoinApplyResponse;
    }
}
