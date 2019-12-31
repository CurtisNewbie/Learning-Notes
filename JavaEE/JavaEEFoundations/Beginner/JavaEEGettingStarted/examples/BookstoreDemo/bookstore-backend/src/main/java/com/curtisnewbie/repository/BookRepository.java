package com.curtisnewbie.repository;

import com.curtisnewbie.model.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;


/**
 * Book is the data model, MySQL DBMS is the data layer, and a repository is the inter layer between the model and the
 * DBMS. CRUD operations are performed here.
 */
public class BookRepository {

    // get EntityManger for a persistence unit
    @PersistenceContext(unitName = "BookstorePU")
    private EntityManager em;

    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    public void delete(Long id) {
        try {
            em.remove(em.getReference(Book.class, id));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Book> findAll() {
        //JPQL
        // b is the alias of single Book
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    public Long countAll() {
        //JPQL
        // b is the alias of single Book
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }
}
