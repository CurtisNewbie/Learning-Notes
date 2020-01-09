package com.curtisnewbie.service;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.util.Digits;
import com.curtisnewbie.util.Loggable;
import com.curtisnewbie.util.Num;
import com.curtisnewbie.util.NumberGenerator;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * Service class for creating books
 */
@Loggable
public class BookService {

    @Inject
    Logger logger;

    @Inject
    @Digits(value = Num.THIRTEEN)
    private NumberGenerator numberGenerator;

    /**
     * Create a new book, and set a Book Number to it.
     *
     * @param title   Title
     * @param price   Price
     * @param descrip Description
     * @return new Book
     */
    public Book createBook(String title, float price, String descrip) {
        Book book = new Book(title, price, descrip);
        book.setNumber(numberGenerator.generateNumber());
        logger.info("Book Created: " + book.toString());
        return book;
    }
}
