package com.ssafy.dadok.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class MeetingUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meetinguser_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    private Long meetingId;

    public static MeetingUser CreateMeetingUser(User user, Long meetingId){
        MeetingUser meetingUser = new MeetingUser();
        meetingUser.user = user;
        meetingUser.meetingId = meetingId;
        return meetingUser;
    }


}
