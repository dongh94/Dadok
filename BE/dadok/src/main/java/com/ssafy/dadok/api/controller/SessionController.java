package com.ssafy.dadok.api.controller;


import com.ssafy.dadok.api.service.BookMeetingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {


    private final BookMeetingService bookMeetingService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String OPEN = "세션이 열려있습니다.";
    private static final String CLOSE = "세션이 닫혀있습니다.";
    private static final String NOT_MEMBER = "내가 가입된 모임이 아닙니다.";


    @ApiOperation(value="세션 열기", notes="호스트가 해당 모임 세션 열기")
    @PostMapping("open/{hostId}")
    public ResponseEntity<String> openSession(@PathVariable String hostId){
        if(bookMeetingService.checkSession(hostId) == 1){
            return new ResponseEntity<>(OPEN, HttpStatus.OK);
        }
        else {
            bookMeetingService.createSession(hostId);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }

    @ApiOperation(value="세션 닫기", notes="호스트가 해당 모임 세션 닫기")
    @PostMapping("close/{hostId}")
    public ResponseEntity<String> closeSession(@PathVariable String hostId){
        if(bookMeetingService.checkSession(hostId) == 0){
            return new ResponseEntity<>(CLOSE, HttpStatus.OK);
        }
        else {
            bookMeetingService.closeSession(hostId);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }

    @ApiOperation(value="세션 입장하기", notes="다른 멤버가 세션 입장하기")
    @PostMapping("enter/{meetId}/{userId}")
    public ResponseEntity<String> enterSession(@PathVariable Long meetId, @PathVariable String userId){
        if(bookMeetingService.enterSession(meetId,userId) == 1)   return new ResponseEntity<>(NOT_MEMBER, HttpStatus.OK);
        if(bookMeetingService.enterSession(meetId,userId) == 2)   return new ResponseEntity<>(CLOSE, HttpStatus.OK);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
