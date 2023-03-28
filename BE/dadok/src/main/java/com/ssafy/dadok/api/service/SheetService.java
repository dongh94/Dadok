package com.ssafy.dadok.api.service;


import com.ssafy.dadok.api.request.SheetUpdateRequest.ImgPathDto;
import com.ssafy.dadok.api.request.SheetUpdateRequest.SheetUpdateRequest;
import com.ssafy.dadok.api.response.MyBookResponse;
import com.ssafy.dadok.api.response.SheetResponse;
import com.ssafy.dadok.db.entity.*;
import com.ssafy.dadok.db.repository.BookMeetingRepository;
import com.ssafy.dadok.db.repository.MeetingUserRepository;
import com.ssafy.dadok.db.repository.SheetRepository;
import com.ssafy.dadok.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SheetService {

    private final BookMeetingRepository bookMeetingRepository;
    private final UserRepository userRepository;
    private final SheetRepository sheetRepository;
    private final MeetingUserRepository meetingUserRepository;



    public int isSheet(String userId){
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());
        return bookMeeting.getIsSheet();
    }
    public int InfoIsSheet(Long meetId){
        BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
        return bookMeeting.getIsSheet();
    }

    @Transactional
    public void create(String userId){
        //user Id를 받와서
        User user = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());
        Sheet sheet = Sheet.createSheet(bookMeeting); //시트 생성
        sheetRepository.save(sheet);
        bookMeeting.setSheet(sheet);
        bookMeeting.setIsSheet();
        bookMeetingRepository.save(bookMeeting);
    }

    public SheetResponse SheetInfo(Long meetId) {
        return SheetResponse.createResponse(bookMeetingRepository.findOne(meetId).getSheet());
    }

    @Transactional
    public void updateSheet(SheetUpdateRequest sheetUpdateRequest){
          User user =  userRepository.findOne(sheetUpdateRequest.getHostId());
          BookMeeting bookMeeting = bookMeetingRepository.findOne(user.getBookMeeting().getId());
        Sheet sheet = sheetRepository.findOne(bookMeeting.getSheet().getId());
        sheet.updateSheet(sheetUpdateRequest);
        bookMeeting.setSheet(sheet);
        sheetRepository.update(sheet);
    }

    @Transactional
    public void complete(String userId) {
        User host = userRepository.findOne(userId);
        BookMeeting bookMeeting = bookMeetingRepository.findOne(host.getBookMeeting().getId());
        Sheet sheet = sheetRepository.findOne(bookMeeting.getSheet().getId());
        Book book = Book.createBook(sheet);
        //
        List<MeetingUser> meetingUserList= meetingUserRepository.findMeetMember(bookMeeting.getId());
        userRepository.update(host);
        //멤버마다 추가
        for (MeetingUser meetingUser : meetingUserList) {
            User members = userRepository.findOne(meetingUser.getUser().getId());
            members.setBook(book);
            members.addMeetCount();
            members.addBookCount();
           userRepository.update(members);
        }
        //북미팅에서 제거
        sheetRepository.delete(sheet.getId());
        bookMeeting.deleteSheet();
        bookMeetingRepository.update(bookMeeting);


    }

    public List<MyBookResponse> getBookList(String userId) {
        List<MyBookResponse> myBookResponses = new ArrayList<>();

        User user = userRepository.findOne(userId);
        List<Book> books= user.getBooks();
        for (Book book : books) {
            myBookResponses.add(MyBookResponse.CreateResponse(book));
        }

        return myBookResponses;
    }

    public int isSheetMeet(Long meetId) {
        BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
        return bookMeeting.getIsSheet();
    }

    @Transactional
    public void updateIsbn(Long meetId, String isbn) {
        BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
        Sheet sheet = bookMeeting.getSheet();
        sheet.updateIsbn(isbn);
        sheetRepository.update(sheet);
        bookMeeting.setSheet(sheet);
        bookMeetingRepository.update(bookMeeting);
    }

    @Transactional
    public void updateImg(Long meetId, ImgPathDto imgPathDto) {
        BookMeeting bookMeeting = bookMeetingRepository.findOne(meetId);
        Sheet sheet = bookMeeting.getSheet();
        sheet.updateImgPath(imgPathDto.getImg_path());
        sheetRepository.update(sheet);
        bookMeeting.setSheet(sheet);
        bookMeetingRepository.update(bookMeeting);
    }
}
