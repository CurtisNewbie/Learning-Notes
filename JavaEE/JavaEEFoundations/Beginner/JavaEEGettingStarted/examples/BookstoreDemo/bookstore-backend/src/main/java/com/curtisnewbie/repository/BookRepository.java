package com.curtisnewbie.repository;

import com.curtisnewbie.model.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;


/**
 * Book is the data model, MySQL DBMS is the data layer, and a repository is the inter layer between the model and the
 * DBMS. CRUD operations are performed here.
 */
public class BookRepository {

    // get EntityManger for a persistence unit
    @PersistenceContext(unitName = "BookstorePU")
    private EntityManager em;

    public Book find(Long id){
        return em.find(Book.class, id);
    }

    public Book create(Book book){
        em.persist(book);
        return book;
    }

    public void delete(Long id){
        try{
            em.remove(em.getReference(Book.class, id));
        }catch(EntityNotFoundException e){
           e.printStackTrace();
        }
    }
}
