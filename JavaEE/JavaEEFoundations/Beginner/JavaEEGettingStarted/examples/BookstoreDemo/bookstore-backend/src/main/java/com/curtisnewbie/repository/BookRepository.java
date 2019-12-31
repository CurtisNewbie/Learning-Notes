package com.curtisnewbie.repository;

import com.curtisnewbie.model.*;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;


/**
 * <p>
 * Book is the data model, MySQL DBMS is the data layer, and a repository is the inter layer between the model and the
 * DBMS. CRUD operations are performed here.
 * </p>
 * <p>@Transactional mark the methods in this repository as transactional, which supports or requires the methods to be
 * within a transaction to ensure data consistency and accuracy. It is just like ACID in Relational database, we do not
 * want to update any associated data, when part of the transaction fails, we want it to be atomic and consistent.</p>
 */
@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepository {

    // get EntityManger for a persistence unit
    @PersistenceContext(unitName = "BookstorePU")
    private EntityManager em;

    // read only, so transactional supports
    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    // create a book, so it needs to be transactional
    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(Book book) {
        em.persist(book);
        return book;
    }

    // delete a book, so it needs to be transactional
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        try {
            em.remove(em.getReference(Book.class, id));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    // read only, so transactional supports
    public List<Book> findAll() {
        //JPQL
        // b is the alias of single Book
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b ORDER BY b.title DESC", Book.class);
        return query.getResultList();
    }

    // read only, so transactional supports
    public Long countAll() {
        //JPQL
        // b is the alias of single Book
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }
}
