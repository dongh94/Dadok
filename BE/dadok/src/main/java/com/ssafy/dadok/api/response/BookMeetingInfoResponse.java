package com.ssafy.dadok.api.response;




import com.ssafy.dadok.db.entity.BookMeeting;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
/*Meeting 상세보기 반환 Dto*/
public class BookMeetingInfoResponse {
    //TODO 내가 가입한 모임을 볼때는  미팅 ID가 있으므로 시트지를 수정할수있게
    private Long id;
    private String name;
    private String theme;
    private String img;
    private String interest;
    private int people;
    private int capacity;
    private String startDay;
    private String introduce; //소개
    private String format ; //진행방식
    private String targetUser; //추천 대상
    private String schedule;
    private String host;
    private LocalDateTime createTime;
    private int session;
    private int isSheet;


    //리스트 - 리스트 매핑 때문에 static 메소드 말고 생성자로 해야함
    public BookMeetingInfoResponse(BookMeeting bookMeeting) {
        this.id = bookMeeting.getId();
        this.name = bookMeeting.getName();
        this.theme = bookMeeting.getTheme();
        this.img = bookMeeting.getImg();
        this.interest = bookMeeting.getInterest();
        this.people = bookMeeting.getPeople();
        this.introduce = bookMeeting.getIntroduce();
        this.startDay = bookMeeting.getStartDay();
        this.format = bookMeeting.getFormat();
        this.targetUser = bookMeeting.getTargetUser();
        this.schedule = bookMeeting.getSchedule();
        this.host = bookMeeting.getHost().getId();
        this.createTime = bookMeeting.getCreateTime();
        this.capacity = bookMeeting.getCapacity();
        this.session = bookMeeting.getSession();
        this.isSheet = bookMeeting.getIsSheet();
    }
}
