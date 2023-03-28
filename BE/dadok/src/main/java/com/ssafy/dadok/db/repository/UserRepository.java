package com.ssafy.dadok.db.repository;

import com.ssafy.dadok.db.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    //회원 가입
    public void save(User user) {
        em.persist(user);
    }

    //회원 정보 조회
    public User findOne(String id) {
            return em.find(User.class, id);
    }
    // 수정
    public void update(User user) {
       //User updateUser = em.find(User.class, user.getId());
       //em.persist(user);
        em.merge(user);
    }

    //탈퇴
    public void delete(String id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    //id로 찾기
    public List<User> findById(String id) {
        return em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id",id).getResultList();
    }
    //email로 찾기
    public List<User> findByEmail(String email){
        return em.createQuery("select u from User u where u.email =:email",User.class)
                .setParameter("email",email).getResultList();
    }

    //이메일 인증하기
    public void authEmail(String email) {
        List<User> userList = em.createQuery("select u from User u where u.email = :email ",User.class)
                .setParameter("email", email).getResultList();
        String id  = userList.get(0).getId();
        User user = em.find(User.class,id);
        user.setEmailAuth();
        em.merge(user);
    }
}
