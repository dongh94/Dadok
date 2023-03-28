package com.ssafy.dadok.api.controller;


import com.ssafy.dadok.api.request.UserRequest.*;
import com.ssafy.dadok.api.response.LoginUserResponse;
import com.ssafy.dadok.api.response.UserIntroduceResponse;
import com.ssafy.dadok.api.service.EmailService;
import com.ssafy.dadok.api.service.JwtService;
import com.ssafy.dadok.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final EmailService emailService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String NO_AUTH = "noauth";

    @PostMapping //회원가입
    public ResponseEntity<Integer> regist(@RequestBody UserJoinRequest userJoin) throws Exception{

        int value = userService.join(userJoin);

        if(value == 1)   return new ResponseEntity<>(value, HttpStatus.OK); //ID중복
        else if(value==2)   return new ResponseEntity<>(value, HttpStatus.OK);
        else {
            //이메일 전송
            emailService.sendMail(userJoin.getEmail());
        }
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
    @PostMapping("/remail/{userId}")//이메일 재전송
    public ResponseEntity<String> remail(@PathVariable String userId) throws Exception{
        String email = userService.remail(userId);
        emailService.sendMail(email);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<String> emailAuth(@PathVariable("email") String email ) throws Exception{
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");


        userService.authEmail(email);
        String msg = "인증이 완료 되었습니다! 홈페이지로 돌아가 로그인을 완료해주세요!";
        return new ResponseEntity<String>(msg,resHeaders, HttpStatus.OK);
    }

    @GetMapping("/{id}") //정보 조회
    public ResponseEntity<LoginUserResponse> userinfo(@PathVariable String id) throws Exception{
        return new ResponseEntity<LoginUserResponse>(userService.findOne(id),HttpStatus.OK);
    }
    @PostMapping("/login") //로그인
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginRequest userLogin) throws Exception{
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        LoginUserResponse loginUser = userService.findOne(userLogin.getId());
        if(loginUser == null){
            status = HttpStatus.ACCEPTED;
            resultMap.put("message", FAIL);
            return new ResponseEntity<Map<String, Object>>(resultMap,resHeaders, status);
        }
        if(userService.confirmAuth(userLogin) == 0){
            resultMap.put("message", NO_AUTH);
            status = HttpStatus.ACCEPTED;
            return new ResponseEntity<Map<String,Object>>(resultMap,status);
        }
       else {
            try {
                if (loginUser.getPw().equals(userLogin.getPw())) { //존재하는 ID && 비번 일치
                    String token = jwtService.create("userid", loginUser.getId(), "access-token"); //key, data, subject
                    resultMap.put("access-token", token);
                    resultMap.put("message", SUCCESS);
                } else {
                    resultMap.put("message", FAIL);
                }
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return new ResponseEntity<Map<String, Object>>(resultMap,resHeaders, status);
        }
    }

    @GetMapping("/login/{userId}") //로그인 유저 정보 조회
    public ResponseEntity<Map<String, Object>> getLoginUserInfo(@PathVariable("userId") String userId, HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            System.out.println("사용 가능한 토큰!!!");
            try {
                resultMap.put("userInfo",  userService.findOne(userId));
                resultMap.put("message", SUCCESS);
            } catch (Exception e) {
                System.out.println("정보조회 실패 : {}"+ e.getMessage());
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            System.out.println("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PatchMapping // 회원 정보 수정
    public ResponseEntity<String> update(@RequestBody LoginUserRequest loginUserRequest) throws Exception{
        userService.update(loginUserRequest);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }
    @GetMapping("/introduce/{userId}")
    public ResponseEntity<UserIntroduceResponse> userIntroduce(@PathVariable("userId") String userId) throws Exception{
        return new ResponseEntity<>(userService.getIntroduce(userId),HttpStatus.OK);
    }
    @PatchMapping ("/introduce/{userId}")//회원 소개 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody IntroduceUpdateRequest userIntroduceRequest, @PathVariable("userId") String userId) throws Exception{
        userService.updateIntroduce(userIntroduceRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    @PatchMapping ("/img/{userId}")//회원 이미지 패스 수정
    public ResponseEntity<String> updateIntroduce(@RequestBody ImgPathUpdateRequest imgPathUpdateRequest, @PathVariable("userId") String userId) throws Exception{

        userService.updateImgPath(imgPathUpdateRequest,userId);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    //회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws Exception{
        userService.delete(id);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }



}
