package com.ssafy.dadok.db.repository;

import com.ssafy.dadok.db.entity.BookMeeting;
import com.ssafy.dadok.db.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookMeetingRepository {

    @PersistenceContext
    private EntityManager em;


    public void save(BookMeeting bookMeeting) {
        em.persist(bookMeeting);
    }

    public BookMeeting findOne(Long id) {
        return em.find(BookMeeting.class, id); //type, pk
    }

    public void update(BookMeeting bookMeeting){
        em.merge(bookMeeting);
    }


    public List<BookMeeting> findAll() {
        return em.createQuery("SELECT m from BookMeeting m", BookMeeting.class)
                .getResultList(); //from의 대상이 table이 아니고 entity
    }

    public void delete(Long id) {
        BookMeeting bookMeeting = em.find(BookMeeting.class, id);
        em.remove(bookMeeting);
    }

    public List<BookMeeting> findMyMeet(User user) {
        return em.createQuery("select m from BookMeeting m where m.host = :user", BookMeeting.class)
                .setParameter("user", user).getResultList();
    }

}
