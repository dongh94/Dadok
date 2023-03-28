package com.ssafy.dadok.api.controller;


import com.ssafy.dadok.api.request.MeetingRequest.*;
import com.ssafy.dadok.api.response.BookMeetingInfoResponse;
import com.ssafy.dadok.api.response.CheckDuplicateResponse;
import com.ssafy.dadok.api.response.MyMeetJoinApplyResponse;
import com.ssafy.dadok.api.response.MyMeetMemberResponse;
import com.ssafy.dadok.api.service.BookMeetingService;
import com.ssafy.dadok.api.service.JoinApplyService;
import com.ssafy.dadok.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookMeetingController {

    private final BookMeetingService bookMeetingService;
    private final UserService userService;
    private final JoinApplyService joinApplyService;


    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    //모임 생성
    @PostMapping
    public ResponseEntity<String> create(@RequestBody MeetingCreateRequest meetingCreateRequest) throws Exception{
        bookMeetingService.create(meetingCreateRequest);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping //모임 조회
    public ResponseEntity<List<BookMeetingInfoResponse>> viewAllMeeting()throws Exception{
        return new ResponseEntity<>(bookMeetingService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/list/{userId}") //내가 가입된 모임 조회하기
    public ResponseEntity<List<BookMeetingInfoResponse>> viewMyMeetingList(@PathVariable String userId) throws Exception{
        return new ResponseEntity<>(bookMeetingService.findMyMeetList(userId),HttpStatus.OK);
    }

    //모임 한개 상세 조회
    @GetMapping("detail/{meetingId}")
    public ResponseEntity<BookMeetingInfoResponse> viewOneDetail(@PathVariable Long meetingId ) throws Exception{
        return new ResponseEntity<BookMeetingInfoResponse>(bookMeetingService.findOne(meetingId), HttpStatus.OK);
    }

    //내가만든 모임 조회
    @GetMapping("myMeet/{userId}")
    public ResponseEntity<BookMeetingInfoResponse> InfoMyMeeting(@PathVariable String userId) throws Exception{
        return new ResponseEntity<BookMeetingInfoResponse>(bookMeetingService.findMyMeet(userId), HttpStatus.OK);
    }

    @PatchMapping("/introduce/{userId}") //미팅 소개 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody MeetIntroduceUpdateRequest meetIntroduceUpdateRequest, @PathVariable("userId") String userId) throws Exception{
        bookMeetingService.updateIntroduce(meetIntroduceUpdateRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }
    @PatchMapping("/format/{userId}") //모임 진행방식 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody FormatUpdateRequest formatUpdateRequest, @PathVariable("userId") String userId) throws Exception{
        bookMeetingService.updateFormat(formatUpdateRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }
    @PatchMapping("/target/{userId}") //대상 유저 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody TargetUpdateRequest targetUpdateRequest, @PathVariable("userId") String userId) throws Exception{
        bookMeetingService.updateTarget(targetUpdateRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }
    @PatchMapping("/schedule/{userId}") //스케쥴 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody ScheduleUpdateRequest scheduleUpdateRequest, @PathVariable("userId") String userId) throws Exception{
        bookMeetingService.updateSchedule(scheduleUpdateRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }


    //모임 제거
   @DeleteMapping("/{meetingId}")
    public ResponseEntity<String> removeMeet(@PathVariable Long meetingId) throws Exception{
        bookMeetingService.delete(meetingId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    //내가 만든 모임의 가입 신청 조회하기
    @GetMapping("/myMeet/apply/{userId}")
    public ResponseEntity<List<MyMeetJoinApplyResponse>> InfoMyMeetApply(@PathVariable String userId) throws Exception{
        return new ResponseEntity<>(userService.getApplyList(userId),HttpStatus.OK);
    }

    //내가 만든 모임의 가입된 멤버 조회하기
    @GetMapping("/myMeet/member/{userId}")
    public ResponseEntity<List<MyMeetMemberResponse>> InfoMyMeetMember(@PathVariable String userId) throws Exception{
        return new ResponseEntity<>(userService.getMemberList(userId),HttpStatus.OK);
    }
    //멤버 한명 추방
    @PostMapping("/ban/{meetId}/{userId}")
    public ResponseEntity<String> banMember(@PathVariable Long meetId, @PathVariable String userId) throws Exception{
        bookMeetingService.ban(meetId,userId);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //가입 신청 이력 조회
    @GetMapping("/meetUser/{meetId}")
    public ResponseEntity<List<CheckDuplicateResponse>>  ApplyAllList(@PathVariable Long meetId) throws Exception{
        return new ResponseEntity<>(joinApplyService.getApplyList(meetId),HttpStatus.OK);
    }

    //세션 열리고 닫힌거 확인하기
    @GetMapping("/session/{hostId}")
    public ResponseEntity<Integer> IsOpenSession(@PathVariable String hostId) throws Exception{
        if(bookMeetingService.IsOpenSession(hostId)==0) return new ResponseEntity<>(0,HttpStatus.OK);
        return new ResponseEntity<>(1,HttpStatus.OK);
    }

}
