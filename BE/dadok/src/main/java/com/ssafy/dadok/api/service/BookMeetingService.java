package com.ssafy.dadok.api.service;


import com.ssafy.dadok.api.request.MeetingRequest.*;
import com.ssafy.dadok.api.response.BookMeetingInfoResponse;
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
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookMeetingService {

    private final BookMeetingRepository bookMeetingRepository;

    private final UserRepository userRepository;
    private final MeetingUserRepository meetingUserRepository;
    private final JoinApplyRepository joinApplyRepository;


    @Transactional
    public void create(MeetingCreateRequest meetingCreate){

            User host = userRepository.findOne(meetingCreate.getHost());
            if(host.getIsCreate() == 1){
                throw new IllegalStateException("이미 모임이 생성되어있습니다.");
            }
            host.setIsCreate(1);
            BookMeeting bookMeeting = BookMeeting.createBookMeeting(meetingCreate, host);

        bookMeetingRepository.save(bookMeeting);
        host.setMeetingUserList(MeetingUser.CreateMeetingUser(host, bookMeeting.getId()));
        userRepository.save(host);
    }

    //존재하는 모든 북미팅 조회
    public List<BookMeetingInfoResponse> findAll(){
        return  bookMeetingRepository.findAll().stream().map(BookMeetingInfoResponse::new).collect(Collectors.toList());
        //b -> new BookMeetingInfoResponse(b`) (b랑 b` 가 같으면 생략해서 ::로 쓸수있음)

    }
    public  BookMeetingInfoResponse findOne(Long id){
        return new BookMeetingInfoResponse( bookMeetingRepository.findOne(id));
    }

    @Transactional
    public void delete(Long id){
        User user = bookMeetingRepository.findOne(id).getHost();
        user.setIsCreate(0);


        List<JoinApply> joinApplies = joinApplyRepository.findBymeetId(id);
        for (JoinApply joinApply : joinApplies) {
            joinApplyRepository.delete(joinApply.getId());
        }
        List<MeetingUser> meetingUserList = meetingUserRepository.findMeetMember(id);
        for (MeetingUser meetingUser : meetingUserList) {
            meetingUserRepository.remove(meetingUser.getId());
        }
        user.removeAllmeeingtUser();
        user.removeAllJoinApplies();

        bookMeetingRepository.delete(id);
        userRepository.save(user);
    }
    public BookMeetingInfoResponse findMyMeet(String userId) {
        User user = userRepository.findOne(userId);
        return new BookMeetingInfoResponse(bookMeetingRepository.findMyMeet(user).get(0));
    }

    //내가 가입한 모임 조회
    @Transactional
    public List<BookMeetingInfoResponse> findMyMeetList(String userId) {
        List<MeetingUser> meetUserList =  meetingUserRepository.findMyMeetList(userRepository.findOne(userId));
        List<BookMeetingInfoResponse> result = new ArrayList<BookMeetingInfoResponse>();
        for (MeetingUser meetingUser : meetUserList) {
            Long meetId = meetingUser.getMeetingId();
            BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
            result.add(new BookMeetingInfoResponse(bookMeeting));
        }
        return result;
    }

    //모임 소개 수정
    @Transactional
    public void updateIntroduce(MeetIntroduceUpdateRequest meetIntroduceUpdateRequest, String userId) {
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());


        bookMeeting.setIntroduce(meetIntroduceUpdateRequest.getIntroduce());
        bookMeetingRepository.update(bookMeeting);
    }
    //모임 진행 방식 수정
    @Transactional
    public void updateFormat(FormatUpdateRequest formatUpdateRequest, String userId) {
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());

        bookMeeting.setFormat(formatUpdateRequest.getFormat());
        bookMeetingRepository.update(bookMeeting);
    }

    //모임 추천 대상 수정
    @Transactional
    public void updateTarget(TargetUpdateRequest targetUpdateRequest, String userId) {
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());

        bookMeeting.setTarget(targetUpdateRequest.getTarget());
        bookMeetingRepository.update(bookMeeting);
    }

    //모임 일정 수정
    @Transactional
    public void updateSchedule(ScheduleUpdateRequest scheduleUpdateRequest, String userId) {
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());

        bookMeeting.setSchedule(scheduleUpdateRequest.getSchedule());
        bookMeetingRepository.update(bookMeeting);
    }

    //멤버 추방
    @Transactional
    public void ban(Long meetId, String userId) {
        User host = bookMeetingRepository.findOne(meetId).getHost();
        host.SetRemoveMember(meetId,userId);
        userRepository.update(host);
        List<MeetingUser> meetUserList =  meetingUserRepository.findMyMeetList(userRepository.findOne(userId));
        for (int i = 0; i < meetUserList.size(); i++) {
            if(meetUserList.get(i).getMeetingId().equals(meetId)){
                meetingUserRepository.remove(meetUserList.get(i).getId());
            }
        }
        BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
        bookMeeting.minusCapacity();
        bookMeetingRepository.update(bookMeeting);
    }

    public int checkSession(String hostId){
        User user = userRepository.findOne(hostId);
        BookMeeting bookMeeting = user.getBookMeeting();
        return bookMeeting.getSession();
    }

    @Transactional
    public void createSession(String hostId) {
        User user = userRepository.findOne(hostId);
        BookMeeting bookMeeting = user.getBookMeeting();
        bookMeeting.setOpenSession();
        bookMeetingRepository.update(bookMeeting);
        userRepository.update(user);
    }
    @Transactional
    public void closeSession(String hostId){
        User user = userRepository.findOne(hostId);
        BookMeeting bookMeeting = user.getBookMeeting();
        bookMeeting.setCloseSession();
        bookMeetingRepository.update(bookMeeting);
        userRepository.update(user);
    }

    public int enterSession(Long meetId,String userId) {

        List<MeetingUser> meetingUserList =  meetingUserRepository.findMeetMember(meetId);
        List<MeetingUser> result = new ArrayList<>();
        for (MeetingUser meetingUser : meetingUserList) {
            if(meetingUser.getUser().getId().equals(userId) && meetingUser.getMeetingId().equals(meetId)){
                result.add(meetingUser);
            }
        }
        if(result.isEmpty()) return 1;
        if(bookMeetingRepository.findOne(meetId).getSession() == 0) return 2;
        return 0;
    }

    public int IsOpenSession(String hostId) {
        User user = userRepository.findOne(hostId);
        return user.getBookMeeting().getSession();
    }
}
