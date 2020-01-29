# Chapter 15 RESTful Web Services

**`Representational State Transfer (REST)` is an architectural style that is applied to web services and relies on protocol.**

**RESTful web services are `stateless`. It reduces the client-server coupling and provides flexibility to scale.**

## Resources and URI

additional src: https://skorks.com/2010/05/what-every-developer-should-know-about-urls/

`Resources` are the central concern in RESTful architecture regardless of where resources are located (e,g., database, local file and so on). A `URI` is a unique identifier pointing to a resource. URIs should be as **descriptive** as possible, the structure of a URI should be able **self-explained** as much as possible.

In this context, a URI consists of:

- `Protocol`, here is HTTP
- `Host`, a DNS name or IP address
- `Port` (optional)
- `Path`, a path pointing to a resource that is delimited by '`/`'
- `Parameters` (optional), parameters uses a seperator of '`?`' at the beginning of the param. It consists of `query` and `fragment`.
  - `Query` is preferred way to send param to the server, each param is a **key:value pair**. Each pair is delimited by '`&`'. E.g., "https://localhost:8080/book`?id=123`", and "https://localhost:8080/book`?id=123&author=john`"
  - `Fragment` is separated from the rest of the URL with a `#` (hash) character. **_(It is never sent to the server, it's used to refer to a specific part of a webpage)_**.

## Representation

Resources may be text, JSON, XML, PDF document, images and so on. Representation of resource URI is a concern when designing the RESTful API. For example, one resource may have different way to represent them (e.g., in different format), we need to consider a way to properly organise them so that the URI is easier to change in the future, properly organised and more importantly, it makes sense.

**_One way to manage this is to `append the format extension to the URI`._**

e.g.,

    // book called "java" in html web page
    http://localhost:8080/book/java

    // book called "java" in pdf format
    http://localhost:8080/book/java/pdf

**_Another way to manage this is through `Content Negotiation`, it will be discussed later._**

## Addressability

URIs should be as addressable as possible, making the resource easily accessible in a uniform way.

## Connectedness

**_"REST advocates that resources should be as connected as possible. If there is a strong relationship between two resources, they should be connected."_**

## Stateless

**_"...every HTTP request happens in complete isolation, as the server should never keep track of requests that were executed before."_**

## HTTP Methods

The four popular request methods are:

- `GET` - request a representation of a resource, it should be idempotent, that calling it mutiple times should not affect the state of the resources.
- `POST` - create a resource, it is not idempotent in that calling it multiple times will end up creating multiple resources. (e.g., new comment, new book to a list)
- `PUT`- update the resource bound to the URI or create the resource for the URI if it doesn't exist. It is idempotent in that using it multiple times will always update the same resource for the same URI.
- `DELETE` - delete a resource, it is idempotent.

The four less frequent methods are:

- `HEAD` - same as GET except that it doesn't have a message body in response, it's good to check validity.
- `TRACE` - echos back the received requests, it's good to see whether any intermediate server or firewall changing the requests.
- `OPTIONS` - request communication options available on the URI
- `CONNECT` - used in conjunction with a proxy to switch to a tunnel, with which HTTP acts as a wrapper for other protocols.

## Content Negotiation

Content Negotiation is defined as `"...the process of selecting the best representation for a given response when there are multiple representations available."`. Content negotiation is based on request headers, e.g,. Accept, Accept-Charset, Accept-Language, e.g.

## Content Type

HTTP uses `Internet Media Types (MIME types)` in the `Content-Type` and `Accept` fields to determine the content types of response.

MIME types are divided into:

- `text/plain`
- `text/html`
- `image/gif`, `image/jpeg`, `image/png`
- `text/xml`
- `application/json`

## Status Code

Status-Code elemenet is a three-digit code describing the context of the response.

- 1xx: `Informational`
- 2xx: `Success`
- 3xx: `Redirection`
- 4xx: `Client error`
- 5xx: `Server error`

## JAX-RS

JAX-RS provides both the server side and client side api to manage interactions with HTTP and REST.

    javax.ws.rs
    javax.ws.rs.client
    javax.ws.rs.container
    javax.ws.rs.core
    javax.ws.rs.ext

Reference implementation of JAX-RS includes: `Jersey`, `CXF`, `RESTEasy`, `Restlet`.

## Writing RESTful Web Services

**"RESTful web services are POJOs that have at least one method annotated with `javax.ws.rs.Path`."**

e.g.,

    @Stateless
    @Path("/book")
    public class BookRestService {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getBookTitle() {
            return "H2G2";
        }
    }

The BookRestService is an end point that has a URI, "/book". Its method also shares this path. In this case, the method _getBookTitle()_ doesn't specifiy a path of itself, so a **GET** request to the path "/book" will invokes this method. This method returns the content type text/plain as the `MediaType.TEXT_PLAIN` is specified.

## Anatomy of a RESTful Web Service

- Class must be annotated with javax.ws.rs.Path
- Class must be public
- Root resource class (with @Path) must have default public constructor
- Must not define finalize() method
- To use EJB capabilities, it must either be @Stateless or @Singleton
- A service must be stateless and not client specific.

## CRUD Operations on RESTful Web Services

Most of the time, we use RESTful web services to return the data that are stored in database. We can make the resource as a **stateless EJB**, so that its methods can then become transactional (as what will happen in EJB) to manage some persistent operations.

e.g.,

    @Path("book")
    @Stateless
    publi class BookRestService{

        // inject JPA EntityManager
        @PersistenceContext(unitName = "dbUnitName");
        private EntityManager em;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getBooks(){
            //...
            List<Book> books = ...;

            return Response.ok(books).build();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createBook(Book book){
            //...
            URI bookUri = ...;
            return return Response.created(bookUri).build();
        }

        @DELETE
        @Path("{id}")
        public Response deleteBook(@PathParam("id") long bookId){
            //...
            return Response.ok().build();
        }
    }

BookRestService is marked with `@Stateless` thus it becomes an `EJB`. A `@Path("book")` is marked as the base URI for this Service class, which is shared among its methods. Method _getBooks()_ is a `GET` request endpoint that **produces** `MediaType.APPLICATION_JSON` of a list of books. Method _createBook()_ is a `POST` request endpoint that **consumes** a JSON object and returns a response of the URI of the persisted book (this is believed to be a good practice). Method _deleteBook()_ is a `DELETE` request endpoint that takes **a parameter named "id"** using the syntax `{ }`, and the annotation `@PathParam()` **match this path parameter to the method parameter bookId**, where the method uses this bookId to delete the book. A method can also take mutiple path parameters, e.g., `@Path{"{bookName}/{id}/{year}"}`, where the URI will be like: _".../book/beginnningJava/12/2013"_.

## URI Definition and Binding URIs

`@Path` annotation represents a relative URI of a resource class (`Root Resource`) or a method.

    @Path("items")
    public class ItemRestService {

        // URI: /items
        @GET
        public List<Item> getItems{ }
    }

In the example above, **@Path("items")** is applied on the class level, thus all methods of this service will have the root url _"/items"_. On top of this, we add subpath for each method if necessary.

    @Path("items")
    public class ItemRestService {

        // URI: /items/books
        @GET
        @Path("/books")
        public List<Item> getItems{ }
    }

**_Paths and HTTP methods will be used to differentiate which service is requested by the client_**.

## Extracting Parameters

Managing accessibility through URIs is not enough, often parameters are extracted and processed in runtime. JAX-RS provides ways to process different parameters such as **path parameters** (delimited by '/') and **query parameters** (separated by '?').

- @javax.ws.rs.`PathParam`
- @javax.ws.rs.`QueryParam`
- @javax.ws.rs.`MatrixParam`
- @javax.ws.rs.`CookieParam`
- @javax.ws.rs.`HeaderParam`
- @javax.ws.rs.`FormParam`

### Path Parameter

**Path Parameters** appends parameters delimited by '/' on the URI of the services. For example, if the base URI is _"/items/book"_, to append one extra path parameter on it, it may be like this _"/items/book/315-23-55"_, where the _"315-23-55"_ may be the id of the book. Path Parameters use the syntax `{ paramName }` to identify the path parameters. In this example, **@Path** can be like this: _`@Path(`"items/book/`{`id`}`"`)`_, then we refer back to this path param in the method parameter using `@PathParam("id") String bookId`.

    E.g.,

    @GET
    @Path("book/{id}")
    public Item getBook(@PathParam("id") String bookId){
        //...
    }

In case where we have **multiple path parameters**, we simply append them in the end.

    @GET
    @Path("book/{author}/{title}")
    public Item getBook(@PathParam("author") String author, @PathParam("title") String title){
        //...
    }

We can apply **Regex** on path parameters following this syntax `{ paramName : regex }`.

    // only accepts at lease one number or '-'
    @GET
    @Path("book/{id : [\\-0-9]{1,}}")
    public Item getBook(@PathParam("id") String bookId){
        //...
    }

### Query Parameter

Query parameters refer to those key/value pairs seperated by `'?'` and delimited by `'&'`. E.g., https://localhost:8080/items/book`?id=123-456-789&author=somebody`.

    e.g.,

    @GET
    @Path("book")
    public Item getBook(@QueryParam("id") String bookId, @QueryParam("author") String somebody){
        //...
    }

### Matrix Parameter

Matric parameters is almost the same as the query parameters, except that it only uses the seperator/delimiter `';'`. E.g., https://localhost:8080/items/book`;id=123-456-789;author=somebody`.

    e.g.,

    @GET
    @Path("book")
    public Item getBook(@MatrixParam("id") String bookId, @MatrixParam("author") String somebody){
        //...
    }

### Form Parameter

additional src: https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-en/cn/part1/chapter5/form_param.html

`@FormParam` is used to access values in a html form.

e.g.,

    <FORM action="http://example.com/customers" method="post">
        <P>
        First name: <INPUT type="text" name="firstname"><BR>
        Last name: <INPUT type="text" name="lastname"><BR>
        <INPUT type="submit" value="Send">
        </P>
    </FORM>

    @POST
    @Path("customers")
    public void createCustomer(@FormParam("firstname") String firstName, @FormParam("lastName") String lastName){
        //...
    }

### HTTP Headers Parameters

`@CookieParam` and `@HeaderParam` resides in HTTP Header. We can extract them using these two annotations.

    e.g.,

    @GET
    public String getSessionId(@CookieParam("sessionID") String sessionId){
        return sessionId;
    }

    @GET
    public String getUserAgent(@HeaderParam("User-Agent") String userAgent){
        return userAgent;
    }

### Default Values for Parametesr

We can define default values for parameters using `@DefaultValue`, if none provided, the default value will be used instead.

    e.g.,

    @GET
    public void getCityParam(@DefaultValue("Sheffield") @QueryParam("city") String city){
        // city = "Sheffield"; if none provided
    }

## Consumes and Produces MediaType

We can consume MIME content by `@javax.ws.rs.Consumes` or produces MIME content by `@javax.ws.rs.Produces`. When either consuming of producing MIME content, we need to declaire the type of content through `@javax.ws.rs.core.MediaType`.

Constants in MediaType

- `APPLICATION_ATOM_XML` (“application/atom+xml”)
- `APPLICATION_FORM_URLENCODED` (“application/x-www-form-urlencoded”)
- `APPLICATION_JSON` “(application/json”)
- `APPLICATION_OCTET_STREAM` (“application/octet-stream”)
- `APPLICATION_SVG_XML` (“application/svg+xml”)
- `APPLICATION_XHTML_XML` (“application/xhtml+xml”)
- `APPLICATION_XML` (“application/xml”)
- `MULTIPART_FORM_DATA` (“multipart/form-data”)
- `TEXT_HTML` (“text/html”)
- `TEXT_PLAIN` “(text/plain”)
- `TEXT_XML` (“text/xml”)
- `WILDCARD` (“\*/\*”)

e.g.,

    @GET
    @Path("book/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response getBook(@PathParam("id") String bookId){
        //...
    }

We can declare mutliple MediaType, and the server will produces the preferred one based on the HTTP request header.

    @GET
    @Path("book/{id}")
    @Produces({MediaType.TEXT_HTML, TEXT_PLAIN})
    public Response getBook(@PathParam("id") String bookId){
        //...
    }

## Returned Types

As shown in some the examples above, we can return Objects as long as we defined the MediaType. However, we should always return `javax.ws.rs.core.Response` to take control of the response code, message and so on.

    e.g.,

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook() {
        //...
        if(book != null)
            return Response.ok(bookObj).build();
        else
            return Response.noContent().build();
    }

## HTTP Method Matching

JAX-RS provides several annotations for HTTP methods: `@GET`, `@POST`, `@PUT`, `@DELETE`, `@HEAD`, `@OPTIONS`. Only public methods can be exposed as RESTful API.

In general, **GET** method retrives resources, **POST** method creates new resources, **PUT** method update existing resources, and **DELETE** method delete a resource.

HTTP specification defines what HTTP response codes should be when the request is successful:

- `GET` should return `200-OK`
- `PUT` should return either `200-OK` or `204-No Content`
- `POST` should return `201-CREATED` with the URI of the created resource or `204-No Content`.
- `DELETE` should return `200-OK`, `202-Accepted` if not yet being conducted, or `204-No Content` if fails.

## Building URIs

JAX-RS provides `javax.ws.rs.core.UriBuilder` that makes it much easier to build URIs.

    e.g.,

    // http://www.server.com/book/1234
    URI uri = UriBuilder.fromUri("http://www.server.com").path("book").path("1234").build();

    // http://www.server.com/book?id=123456
    UriBuilder.fromUri("http://www.server.com").path("book").queryParam("id", "123456").build();

    // http://www.server.com/book;id=123456
    UriBuilder.fromUri("http://www.server.com").path("book").matrixParam("id", "123456").build();

    // http://www.server.com/book?id=123456
    UriBuilder.fromUri("http://www.server.com").path("book").queryParam("id", "123456").build();

    // /book/1234
    UriBuilder.fromResource(BookRestService.class).path("1234").build();

## Contextual Information

Contextual information can be injected into the resource class and methods using the annotation `@javax.ws.rs.core.Context`.

The contextual information that can be injected through `@Context`:

- `HttpHeaders`
- `UriInfo`
- `Request`
- `SecurityContext`
- `Providers`

e.g.,

    //get information about Http headers
    @GET
    public String getDefaultMediaType(@Context HttpHeaders headers){
        List<MediaType> mediaTypes = headers.getAcceptableMediaTypes();
        return mediaTypes.get(0).toString();
    }

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCustomer(Customer c){
        // persist
        customerEJB.persist(c);

        // build a URI for the this persisted resource
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(customer.getId()).build():
        return Response.created(bookUri).build();
    }

## Handling Excpetions

**Unchecked Exceptions** can be catched and rethrown so that they are handled by the container. However, **Checked Exceptions** can not be rethrown directly, they must be wrapped in a **container-specific excpetion** (`ServletException`, `WebServiceExcpetion` or `WebApplicationExcpetion`).

    javax.ws.rs.WebApplicationExcpetions

The subclasses of WebApplicationExceptions include:

- `BadRequesException`
- `ForbiddenException`
- `NotAcceptableException`
- `NotAllowedException`
- `NotAuthorizedException`
- `NotFoundException`
- `NotSupportedException`

These WebApplicationExceptions will be converted to HTTP response by the container and sent back the client. We can also specify the response code to throw a WebApplicationExceptions through `javax.ws.rs.core.Response.Status`.

    e.g.,
    @Path("{id}")
    public Customer getCustomer(@PathParam("id") long customerId){
        if(id < 1000){
            throw new WebApplicationException(Response.Status.NOT_ACCEPTABLE);
        }
    }

## Client API

    javax.ws.rs.client

We will use three main classes: `Client`, `WebTarget`, `Response`. **Client** class is a builder class for WebTarget. A **WebTarget** represents a distinct URI where we are sending requests to. And the **Response** class is used to check the HTTP status.

e.g.,

    // boostrap Client
    Client client = ClientBuilder.newClient();

    // get target for RESTful services
    WebTarget target = client.target("htttp://www.myserver.com/book");

    // build a request invocation class
    Invocation invocation = target.request(MediaType.TEXT_PLAIN).buildGet();

    // invoke HTTP methods
    Response response = invocation.invoke();

    // get data from response
    Book book = resposne.readEntity(Book.class);

## Async RESTful Web Services

additional resource: https://dzone.com/articles/jax-rs-20-asynchronous-server-and-client

We can enables async response for RESTful api using `@Suspended AsyncResponse response`, where the container is told that the response is async and doesn't need to be sent out to the client immediately.

For example,

    public class AsyncResource{

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public Response getMessage(@Suspended AsyncResponse asyncResponse){
            new Thread(() -> {
                asyncResponse.resume(Response.ok("Heavy Task").build());
            }).start();
        }
    }

Generally speaking, `@Suspended` annotation injects the `AsyncResponse` object that asks the container to detach the operation from the current thread, and the `asyncResponse.resume(response)` resumse the response when the work is done in another thread. On top of this, as the operation is detached, we programmatically create a thread for the task.

## Configuring JAX-RS

We can configure the root path for RESTful web services by have a class that extends `javax.ws.rs.core.Application`, and defines the `@ApplicationPath`.

    @ApplicationPath("api")
    public class AppConfig extends Application{

    }
