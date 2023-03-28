package com.ssafy.dadok.db.repository;


import com.ssafy.dadok.db.entity.BookMeeting;
import com.ssafy.dadok.db.entity.MeetingUser;
import com.ssafy.dadok.db.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MeetingUserRepository {

    @PersistenceContext
    private EntityManager em;

    //가입 승인
    public void save(MeetingUser meetingUser){
        em.persist(meetingUser);
    }

    public MeetingUser findOne(Long id){
        return em.find(MeetingUser.class,id);
    }

    //내가 가입한 모임 조회하기 //MeetingUser 반환
    public List<MeetingUser> findMyMeetList(User user){
        return em.createQuery("select m from MeetingUser  m where m.user =: user", MeetingUser.class)
                .setParameter("user", user).getResultList();
    }
    //특정 모임에 가입한 MeetingUser 조회하기
    public List<MeetingUser> findMeetMember(Long meetingId){
        return em.createQuery("select m from MeetingUser m where m.meetingId =: meetingId",MeetingUser.class)
                .setParameter("meetingId",meetingId).getResultList();
    }
    public void remove(Long id){
        em.remove(em.find(MeetingUser.class,id));
    }



}
