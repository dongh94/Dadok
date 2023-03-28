package com.ssafy.dadok.api.controller;

import com.ssafy.dadok.api.request.SheetUpdateRequest.ImgPathDto;
import com.ssafy.dadok.api.request.SheetUpdateRequest.SheetUpdateRequest;
import com.ssafy.dadok.api.request.UserRequest.IntroduceUpdateRequest;
import com.ssafy.dadok.api.response.MyBookResponse;
import com.ssafy.dadok.api.response.SheetResponse;
import com.ssafy.dadok.api.service.SheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sheet")
@RequiredArgsConstructor
public class SheetController {

    private final SheetService sheetService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String SHEET = "시트지가 이미 존재합니다.";
    private static final String NOT_SHEET = "만들어진 시트지가 없습니다.";
    //시트지 생성
    @PostMapping("/{userId}")
    public ResponseEntity<String> create(@PathVariable String userId){
        if(sheetService.isSheet(userId) == 1) return new ResponseEntity<>(SHEET, HttpStatus.OK);
        else {
            sheetService.create(userId);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
    }
    //시트지 존재유무
    @GetMapping("is/{meetId}")
    public ResponseEntity<Integer> isSheetApi(@PathVariable Long meetId){
        return new ResponseEntity<>(sheetService.isSheetMeet(meetId),HttpStatus.OK);
    }


    //시트지조회
    @GetMapping("/{meetId}")
    public ResponseEntity<SheetResponse> viewSheet(@PathVariable Long meetId) throws Exception{
        return new ResponseEntity<>(sheetService.SheetInfo(meetId),HttpStatus.OK);
    }
    //시트지 수정
    @PatchMapping
    public ResponseEntity<String> updateSheet(@RequestBody SheetUpdateRequest sheetUpdateRequest) throws Exception{
        sheetService.updateSheet(sheetUpdateRequest);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    //시트지 저장해서 각 유저에게 보내주기
    @PostMapping("/complete/{hostId}")
    public ResponseEntity<String> complete(@PathVariable String hostId){
        sheetService.complete(hostId);
        return new ResponseEntity<>(SUCCESS,HttpStatus.OK);
    }

    //각 멤버들이 그동안 진행한 시트 확인하기
    @GetMapping("/book/{userId}")
    public ResponseEntity<List<MyBookResponse>> viewMyBooks(@PathVariable String userId) throws Exception{
        return new ResponseEntity<>(sheetService.getBookList(userId),HttpStatus.OK);
    }

    //시트지 isbn만 수정
    @PatchMapping("/{meetId}/{isbn}")
    public ResponseEntity<String> updateIsbn(@PathVariable("meetId") Long meetId, @PathVariable("isbn") String isbn) throws Exception{
        sheetService.updateIsbn(meetId,isbn);
        return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
    }

    @PatchMapping("/img/{meetId}")
    public ResponseEntity<String> updateImg(@PathVariable("meetId") Long meetId, @RequestBody ImgPathDto imgPathDto) throws Exception{
        sheetService.updateImg(meetId, imgPathDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }


}
