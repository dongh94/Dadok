package com.ssafy.dadok.db.repository;


import com.ssafy.dadok.db.entity.Sheet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SheetRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Sheet sheet){
        em.persist(sheet);
    }

    public Sheet findOne(Long id){
        return em.find(Sheet.class,id);
    }

    public void update(Sheet sheet){
        em.merge(sheet);
    }

    public void delete(Long id){
        Sheet sheet = em.find(Sheet.class, id);
        em.remove(sheet);
    }


}
