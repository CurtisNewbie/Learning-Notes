package com.curtisnewbie.app;

import com.curtisnewbie.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookstorePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Book book = new Book();
        book.setTitle("DummyTitle");
        em.persist(book);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
