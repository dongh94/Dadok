package com.ssafy.dadok.db.repository;


import com.ssafy.dadok.db.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Book book){em.persist(book);}

    public void findOne(Long id){em.find(Book.class,id);}

}
