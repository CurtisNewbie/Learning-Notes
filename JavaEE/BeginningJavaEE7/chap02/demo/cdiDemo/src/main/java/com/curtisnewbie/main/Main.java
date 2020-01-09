package com.curtisnewbie.main;

import com.curtisnewbie.service.BookService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        BookService bookService = container.instance().select(BookService.class).get();
        bookService.createBook("H2G2", 12.5f, "Geeky scifi Book");
        weld.shutdown();
    }
}
