package com.curtisnewbie.rest;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

import static io.swagger.annotations.SwaggerDefinition.Scheme.HTTP;
import static io.swagger.annotations.SwaggerDefinition.Scheme.HTTPS;

@SwaggerDefinition(
        info = @Info(title = "API", version = "0.0.1", description = "BookStore api"),
        host = "localhost:8080",
        basePath = "/bookstore-backend-0.0.1/api",
        schemes = {HTTP, HTTPS}
)

/**
 * RestApi
 */
@Path("/books")
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("Get all books")
    @ApiResponse(code = 204, message = "No books")
    /** create response in forms of Json */
    public Response getBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.size() > 0) {
            return Response.ok(books).build();
        } else {
            // return 204 HTTP response
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get one book by id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("id") long id) {
        Book book = bookRepository.find(id);
        return Response.ok(book).build();
    }

    @GET
    @Path("/count")
    @ApiOperation("Get number of books")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countBooks() {
        long numOfBooks = bookRepository.countAll();
        return Response.ok(numOfBooks).build();
    }

    // create dummy data
    @GET
    @Path("/dummy")
    @ApiOperation(value = "Create a \"dummy\" book/data in DB", response = Book.class)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDummy() {
        Book dummy = new Book();
        dummy.setTitle("DummyTitle");
        dummy.setUnitCost(15f);
        dummy.setDescription("blablabla");
        bookRepository.create(dummy);
        return Response.ok(dummy).build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete a book by id")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteBook(@PathParam("id") long id) {
        bookRepository.delete(id);
        return Response.ok("id:" + id + " deleted").build();
    }

    @POST
    @ApiOperation(value = "Create the given book in DB", response = Book.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.ok(createdURI).build();
    }
    /* use this for testing POST request, don't include id as it's auto generated
    {
        "description": "apple",
            "isbn": "111-111-5231481459",
            "title": "DummyTitle1929597741",
            "unitCost": 9.99999
    }

    curl -H "Content-Type: application/json" -X POST http://localhost:8080/bookstore-backend-0.0.1/api/books/ -d
    '{"description":"apple", "isbn":"111-111-5231481459","title":"DummyTitle1929597741","unitCost":9.99999}'
    */

}
