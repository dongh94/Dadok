package com.ssafy.dadok.db.entity;

import com.ssafy.dadok.api.request.JoinAcceptRequest.JoinAcceptRequest;
import com.ssafy.dadok.api.request.UserRequest.UserJoinRequest;
import com.ssafy.dadok.api.request.UserRequest.LoginUserRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor //파라미터 없는 생성자
@Getter
public class User{

    @Id @Column(name = "user_id")
    private String id;

    private String pw;
    private String name;
    private String birthday;
    private String interest;
    private String email;
    private String img_path;
    private String sex;
    private String introduce; //자기 소개 칸 추가
    private int isCreate = 0;
    private int Auth = 0;

    //여기서만 가능한 관련 로직은 Entity에 넣어두자!
    private int meeting_count; //모임 참여 횟수
    private int meeting_time; //모임 참여 시간
    private int book_count; //읽은책수

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//독서모임과 매핑을 위한 중간테이블
     List<MeetingUser> meetingUserList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY) //가입 신청
    private List<JoinApply> joinApplies = new ArrayList<>();
    //가입 신청은 User 가 확인할수 있도록 단방향 매핑

    @OneToMany(cascade = CascadeType.ALL) //User가 그동안 읽은 책들
    private List<Book> books = new ArrayList<>();


    @OneToOne(mappedBy = "host",cascade = CascadeType.ALL)
    private BookMeeting bookMeeting;

    public void setJoinApplies(JoinApply joinApplies) {
        this.joinApplies.add(joinApplies);
    }
    public void setBook(Book book){
        this.books.add(book);
    }

    public void setRemoveApplies(JoinAcceptRequest joinApply, Long bookMeetingId){
        for (int i = 0; i < joinApplies.size(); i++) {
            if(joinApplies.get(i).getUserId().equals(joinApply.getApplicantId()) && joinApplies.get(i).getMeetingId().equals(bookMeetingId)){
                this.joinApplies.remove(i);
            }
        }
    }
    public void removeAllJoinApplies(){
        for (int i = this.joinApplies.size()-1; i >=0 ; i--) {
            this.joinApplies.remove(this.joinApplies.get(i));
        }
    }
    public void removeAllmeeingtUser(){
        for(int i = this.meetingUserList.size()-1;i>=0;i--){
            this.meetingUserList.remove(this.meetingUserList.get(i));
        }
    }

    public  void setIntroduce(String introduce){
        this.introduce = introduce;
    }
    public void setIsCreate(int value){
        this.isCreate = value;
    }
    public void setEmailAuth(){this.Auth = 1;}

    //이거지금연과관계가이상함 User랑 MeetingUser랑
    public void setMeetingUserList(MeetingUser meetingUser){
        this.meetingUserList.add(meetingUser);
    }



    //가입요청 생성 메서드
    public static User setJoin(UserJoinRequest userJoinRequest){
        User user = new User();
        user.id = userJoinRequest.getId();
        user.pw = userJoinRequest.getPw();
        user.name = userJoinRequest.getName();
        user.birthday = userJoinRequest.getBirthday();
        user.interest = userJoinRequest.getInterest();
        user.email = userJoinRequest.getEmail();
        user.img_path = userJoinRequest.getImg_path();
        user.sex = userJoinRequest.getSex();
        user.introduce=userJoinRequest.getIntroduce();
        user.meeting_time = 0;
        user.meeting_count = 0;
        user.book_count = 0;
        user.isCreate = 0;
        user.Auth = 0;
        return user;
    }
    //수정요청 생성 메서드
    public static User setUpdate(LoginUserRequest loginUserRequest){
        User user = new User();
        user.id = loginUserRequest.getId();
        user.pw = loginUserRequest.getPw();
        user.name = loginUserRequest.getName();
        user.birthday = loginUserRequest.getBirthday();
        user.interest = loginUserRequest.getInterest();
        user.email = loginUserRequest.getEmail();
        user.img_path = loginUserRequest.getImg_path();
        user.sex = loginUserRequest.getSex();
        user.meeting_time = loginUserRequest.getMeeting_time();
        user.meeting_count = loginUserRequest.getMeeting_count();
        user.book_count = loginUserRequest.getBook_count();
        user.isCreate = loginUserRequest.getIsCreate();
        user.Auth = loginUserRequest.getEmailAuth();
        return user;
    }


    public void addMeetCount(){
        this.meeting_count += 1;
    }
    public void addMeetTime(int time){
        this.meeting_time += time;
    }
    public void addBookCount(){
        this.book_count += 1;
    }

    public void SetRemoveMember(Long meetId, String userId) {
        for (int i = 0; i < this.meetingUserList.size() ; i++) {
            if(meetingUserList.get(i).getUser().getId().equals(userId) &&
            meetingUserList.get(i).getMeetingId().equals(meetId)){
                meetingUserList.remove(i);
             }
        }
    }

    public void setImgPath(String imgPath) {
        this.img_path = imgPath;
    }


}
