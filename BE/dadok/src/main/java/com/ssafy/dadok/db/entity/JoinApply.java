package com.ssafy.dadok.db.entity;

import com.ssafy.dadok.api.request.JoinApplyRequest.JoinApplyCreateRequest;
import com.ssafy.dadok.api.service.JoinApplyService;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class JoinApply {


    @Id @GeneratedValue
    @Column(name = "joinapply_id")
    private Long id;

    private String userId;
    private Long meetingId;


    //생성 메서드
    public static JoinApply CreateApply(String applicantId, Long MeetingId){
        JoinApply joinApply = new JoinApply();
        joinApply.userId = applicantId;
        joinApply.meetingId = MeetingId;
        return joinApply;
    }



}
