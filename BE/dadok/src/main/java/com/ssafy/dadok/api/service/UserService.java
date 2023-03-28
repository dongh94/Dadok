package com.ssafy.dadok.api.service;

import com.ssafy.dadok.api.request.UserRequest.*;
import com.ssafy.dadok.api.response.LoginUserResponse;
import com.ssafy.dadok.api.response.MyMeetJoinApplyResponse;
import com.ssafy.dadok.api.response.MyMeetMemberResponse;
import com.ssafy.dadok.api.response.UserIntroduceResponse;
import com.ssafy.dadok.db.entity.JoinApply;
import com.ssafy.dadok.db.entity.MeetingUser;
import com.ssafy.dadok.db.entity.User;
import com.ssafy.dadok.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final 필드만 가지고 생성자를 만들어 줌
public class UserService {

    private final UserRepository userRepository;

     //회원가입
    @Transactional
    public int join(UserJoinRequest userJoin) {
        if(validatedDuplicateUser(userJoin.getId())==1) return 1;
        if(validateDuplicateEmail(userJoin.getEmail())==1) return 2;

        User user = User.setJoin(userJoin);
        userRepository.save(user);
        return 0;
    }

   private int validatedDuplicateUser(String userId){
        List<User> findUsers = userRepository.findById(userId);
        if(!findUsers.isEmpty()){
//            throw new IllegalStateException("이미 존재하는 아이디입니다.");
            return 1;
        }
        return 0;
    }
    private int validateDuplicateEmail(String email){
        List<User> findEmails = userRepository.findByEmail(email);
        if(!findEmails.isEmpty()) return 1;
        return 0;
    }

    //user 정보 조회
    public LoginUserResponse findOne(String userId) {
      return  LoginUserResponse.CreateLoginUser(userRepository.findOne(userId));
    }


    @Transactional
    public void update(LoginUserRequest loginUserRequest) {
        User user = User.setUpdate(loginUserRequest);
        userRepository.update(user);
    }


    @Transactional
    public void delete(String userId) {
        userRepository.delete(userId);
    }


    public List<MyMeetJoinApplyResponse> getApplyList(String userId) {
        //host
        User user = userRepository.findOne(userId);
        //반환해줄 List
        List<MyMeetJoinApplyResponse> myMeetJoinApplyResponses = new ArrayList<>();
        //Response 생성용 List
        for (JoinApply joinApply : user.getJoinApplies()) {
            //가입 신청자 목록 얻어오기
            User applicant = userRepository.findOne(joinApply.getUserId());
            myMeetJoinApplyResponses.add(MyMeetJoinApplyResponse.createResponse(applicant));
        }
        return myMeetJoinApplyResponses;
    }

    public List<MyMeetMemberResponse> getMemberList(String userId) {
        //host
        User user = userRepository.findOne(userId);
        //반환해줄 List
        List<MyMeetMemberResponse> myMeetMemberResponses = new ArrayList<>();
        //Response 생성용 List
        for (MeetingUser meetingUser : user.getMeetingUserList()) {
            //가입 승인된 목록 얻어오기
            User member = meetingUser.getUser();
            myMeetMemberResponses.add(MyMeetMemberResponse.CreateMyMeetMemberResponse(member));
        }
        return myMeetMemberResponses;
    }

    public UserIntroduceResponse getIntroduce(String userId) { //유저정보소개
        User user = userRepository.findOne(userId);
        return UserIntroduceResponse.userIntroduceResponse(user);
    }

    @Transactional
    public void updateIntroduce(IntroduceUpdateRequest introduceUpdateRequest,String userId){
        User user = userRepository.findOne(userId);
        user.setIntroduce(introduceUpdateRequest.getIntroduce());
        userRepository.update(user);
    }

    @Transactional
    public void authEmail(String email) {
        userRepository.authEmail(email);
    }

    public int confirmAuth(LoginRequest userLogin) {
        User user = userRepository.findOne(userLogin.getId());
        return user.getAuth();
    }

    @Transactional
    public void updateImgPath(ImgPathUpdateRequest imgPathUpdateRequest, String userId) {
        User user = userRepository.findOne(userId);
        user.setImgPath(imgPathUpdateRequest.getImg_path());
        userRepository.update(user);
    }

    public String remail(String userId) {
        User user = userRepository.findOne(userId);
        return user.getEmail();
    }
}
