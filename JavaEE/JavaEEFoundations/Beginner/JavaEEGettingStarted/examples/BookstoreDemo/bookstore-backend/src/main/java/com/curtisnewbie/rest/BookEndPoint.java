package com.curtisnewbie.rest;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * RestApi
 */
@Path("/books")
public class BookEndPoint {

    @Inject
    private BookRepository bookRespository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /** create response in forms of Json */
    public Response getBooks() {
        return Response.status(Response.Status.NO_CONTENT).build();
        //        List<Book> books = bookRespository.findAll();
//        if (books != null && books.size() > 0) {
//            return Response.ok(books).build();
//        } else {
//            // return 204 HTTP response
//            return Response.status(Response.Status.NO_CONTENT).build();
//        }
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countBooks() {
        return Response.ok(100000L).build();
        //        long numOfBooks = bookRespository.countAll();
//        if (numOfBooks <= 0)
//            return Response.noContent().build();
//        else
//            return Response.ok(numOfBooks).build();
    }


}
