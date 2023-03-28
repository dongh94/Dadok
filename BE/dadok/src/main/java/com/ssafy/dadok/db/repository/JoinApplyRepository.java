package com.ssafy.dadok.db.repository;


import com.ssafy.dadok.db.entity.JoinApply;
import com.ssafy.dadok.db.entity.MeetingUser;
import com.ssafy.dadok.db.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JoinApplyRepository {

    @PersistenceContext
    private EntityManager em;

    public JoinApply findOne(Long id){
        return em.find(JoinApply.class,id);
    }

    //가입 신청
    public void save(JoinApply joinApply){
        em.persist(joinApply);
    }


//
//    //가입 신청 조회
//    public List<JoinApply> findById(String hostId) {
//        return em.createQuery("select u from JoinApply u where u.id = :hostId", JoinApply.class)
//                .setParameter("hostId",hostId).getResultList();
//    }

    //가입 신청 삭제
    public void delete(Long id){
        em.remove(em.find(JoinApply.class,id));
    }

    public List<JoinApply> findBymeetId(Long meetingId) {
        return em.createQuery("select m from JoinApply  m where m.meetingId = :meetingId",JoinApply.class)
                .setParameter("meetingId",meetingId).getResultList();

    }


}
