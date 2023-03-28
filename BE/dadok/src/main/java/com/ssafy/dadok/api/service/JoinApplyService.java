package com.ssafy.dadok.api.service;


import com.ssafy.dadok.api.request.JoinAcceptRequest.JoinAcceptRequest;
import com.ssafy.dadok.api.request.JoinApplyRequest.JoinApplyCreateRequest;
import com.ssafy.dadok.api.response.CheckDuplicateResponse;
import com.ssafy.dadok.db.entity.BookMeeting;
import com.ssafy.dadok.db.entity.JoinApply;
import com.ssafy.dadok.db.entity.MeetingUser;
import com.ssafy.dadok.db.entity.User;
import com.ssafy.dadok.db.repository.BookMeetingRepository;
import com.ssafy.dadok.db.repository.JoinApplyRepository;
import com.ssafy.dadok.db.repository.MeetingUserRepository;
import com.ssafy.dadok.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JoinApplyService {

    private final JoinApplyRepository joinApplyRepository;
    private final UserRepository userRepository;
    private final BookMeetingRepository bookMeetingRepository;
    private final MeetingUserRepository meetingUserRepository;


    //가입 신청 생성
    @Transactional
    public int create(JoinApplyCreateRequest joinApplyCreateRequest){

        BookMeeting bookMeeting = bookMeetingRepository.findOne(joinApplyCreateRequest.getMeetingId());
        //인원수가 full 일 경우 가입 신청 방지
        if(bookMeeting.getPeople() == bookMeeting.getCapacity()){
            return 1;
        }

        else {
            //이미 가입 신청을 한 모임인 경우
            User host = bookMeetingRepository.findOne(joinApplyCreateRequest.getMeetingId()).getHost();
            List<JoinApply> joinApplies = host.getJoinApplies();
            for (JoinApply joinApply : joinApplies) {
                if(joinApply.getUserId().equals(joinApplyCreateRequest.getApplicantId())){
                    return 2;
                }
            }
            //이미 가입된 모임 일 경우
            //TODO 아마 여기도 바꿔야할듯
            List<MeetingUser> meetingUserList = host.getMeetingUserList();
            for (MeetingUser meetingUser : meetingUserList) {
                if(meetingUser.getUser().getId().equals(joinApplyCreateRequest.getApplicantId())) return 3;
            }
            JoinApply joinApply = JoinApply.CreateApply(joinApplyCreateRequest.getApplicantId(), joinApplyCreateRequest.getMeetingId());
            joinApplyRepository.save(joinApply);
            //모임 호스트에게 가입 신청 모임 전달
            host.setJoinApplies(joinApply);
            //DB반영
            userRepository.update(host);
            return 0;
        }
    }

    //가입 승인
    @Transactional
    public int accept(JoinAcceptRequest joinAcceptRequest) {
        User host = userRepository.findOne(joinAcceptRequest.getHostId());
        User user = userRepository.findOne(joinAcceptRequest.getApplicantId()); //지원자
        Long BookMeetingId = host.getBookMeeting().getId();
        BookMeeting bookMeeting = bookMeetingRepository.findOne(BookMeetingId);
        if(bookMeeting.getCapacity() == bookMeeting.getPeople()){
            return 1;
        }
        else {
            host.getBookMeeting().addCapacity();
            //테이블에 저장
            MeetingUser meetingUser = MeetingUser.CreateMeetingUser(user, BookMeetingId);
            meetingUserRepository.save(meetingUser);
            //가입승인 멤버에 저장
            host.setMeetingUserList(meetingUser);
            //가입 신청에서는 제거
            host.setRemoveApplies(joinAcceptRequest, host.getBookMeeting().getId());
            userRepository.update(host);
            return 0;
        }
    }
    //가입 거절
    @Transactional
    public void reject(JoinAcceptRequest joinAcceptRequest){
        User host = userRepository.findOne(joinAcceptRequest.getHostId());
        host.setRemoveApplies(joinAcceptRequest, host.getBookMeeting().getId());
    }

    public List<CheckDuplicateResponse> getApplyList(Long meetId) {
        List<CheckDuplicateResponse> checkDuplicateResponses = new ArrayList<>();
        List<JoinApply> joinApplyRepositories = joinApplyRepository.findBymeetId(meetId);
        for (JoinApply applyRepository : joinApplyRepositories) {
            checkDuplicateResponses.add(CheckDuplicateResponse.createResponse(applyRepository.getUserId()));
        }
        return checkDuplicateResponses;
    }
}
