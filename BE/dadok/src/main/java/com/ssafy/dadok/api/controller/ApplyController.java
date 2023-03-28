package com.ssafy.dadok.api.controller;


import com.ssafy.dadok.api.request.JoinAcceptRequest.JoinAcceptRequest;
import com.ssafy.dadok.api.request.JoinApplyRequest.JoinApplyCreateRequest;
import com.ssafy.dadok.api.service.JoinApplyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apply")
@CrossOrigin("*")
public class ApplyController {

    private final JoinApplyService joinApplyService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    @ApiOperation(value="가입 신청", notes="가입하고자 하는 모임에 가입신청 제출")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping
    public ResponseEntity<String> create(@RequestBody JoinApplyCreateRequest joinApplyCreateRequest) throws Exception{
        int status = joinApplyService.create(joinApplyCreateRequest);
        if(status == 1)  return new ResponseEntity<>("모임 인원 가득찼습니다.", HttpStatus.OK);
        else if(status == 2) return new ResponseEntity<>("이미 가입 신청한 모임입니다.",HttpStatus.OK);
        else if(status == 3) return new ResponseEntity<>("이미 가입된 모임입니다.",HttpStatus.OK);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
    @ApiOperation(value="가입 신청 수락", notes="해당 모임의 호스트가 가입 신청 승인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/accept")
    public ResponseEntity<String> accept(@RequestBody JoinAcceptRequest joinAcceptRequest) throws Exception{
        if(joinApplyService.accept(joinAcceptRequest)==1){
            return new ResponseEntity<>("모임 인원 가득찼습니다.", HttpStatus.OK);
        }
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }
    //가입 신청 거절
    @ApiOperation(value="가입 신청 거절", notes="해당 모임의 호스트가 가입 신청 거절")
    @ApiResponses({
            @ApiResponse(code = 200, message = "API 정상 작동"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/reject")
    public ResponseEntity<String> reject(@RequestBody JoinAcceptRequest joinAcceptRequest)throws Exception{
        joinApplyService.reject(joinAcceptRequest);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

}
