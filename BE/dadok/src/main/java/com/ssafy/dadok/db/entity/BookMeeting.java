package com.ssafy.dadok.db.entity;

import com.ssafy.dadok.api.request.MeetingRequest.MeetingCreateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class BookMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmeeting_id")
    private Long id;

    private String name;
    private String theme;
    private String img;
    private String interest;
    private int people;
    private int capacity;
    private String startDay;
    @Column(length = 1000)
    private String introduce; //소개
    @Column(length = 1000)
    private String format ; //진행방식
    @Column(length = 1000)
    private String targetUser; //추천 대상
    private String schedule;
    private LocalDateTime createTime;
    private int isSheet;
    private int session;
    //id는 자동생성 //host는 get id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User host;

    //TODO 북 미팅에서 지우면 시트 테이블에서도 없어지는지 확인하고 싶어요. Cascade
    @OneToOne(mappedBy = "bookMeeting",cascade = CascadeType.ALL)
    private Sheet sheet;

    
    //(mappedBy = "meeting")
    //TODO 필요없음
    /*@OneToMany(cascade = CascadeType.ALL)
    private List<MeetingUser> meetingUserList = new ArrayList<>();*/


    public void setSheet(Sheet sheet){
        this.sheet = sheet;
    }

    public void setIsSheet(){
        this.isSheet = 1;
    }
    public void deleteSheet(){
       //this.sheet.
        this.sheet = null;
        this.isSheet = 0;
    }

    //모임 생성 메서드
    public static BookMeeting createBookMeeting(MeetingCreateRequest meetingCreateRequest, User user){
        BookMeeting bookMeeting = new BookMeeting();
        bookMeeting.name = meetingCreateRequest.getName();
        bookMeeting.theme = meetingCreateRequest.getTheme();
        bookMeeting.img = meetingCreateRequest.getImg();
        bookMeeting.interest = meetingCreateRequest.getInterest();
        bookMeeting.people = meetingCreateRequest.getPeople(); //최대정원
        bookMeeting.startDay = meetingCreateRequest.getStartDay();
        bookMeeting.capacity = 1;
        bookMeeting.introduce = meetingCreateRequest.getIntroduce();
        bookMeeting.format = meetingCreateRequest.getFormat();
        bookMeeting.targetUser = meetingCreateRequest.getTargetUser();
        bookMeeting.schedule = meetingCreateRequest.getSchedule();
        bookMeeting.createTime = LocalDateTime.now();
        bookMeeting.host = user;
        bookMeeting.isSheet = 0;
        //host 본인도 들어가야함
        return bookMeeting;
    }
    /*public void addMeetUser(User user, Long meetId){
        this.meetingUserList.add(MeetingUser.CreateMeetingUser(user,meetId));
    }*/

    public void addCapacity(){
        this.capacity++;
    }
    public void minusCapacity(){
        this.capacity--;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setTarget(String target) {
        this.targetUser = target;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setOpenSession() {
        this.session = 1;
    }
    public void setCloseSession(){
        this.session = 0;
    }
}
