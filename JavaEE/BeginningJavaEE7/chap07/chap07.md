# Chapter 7 Enterprise JavaBeans

add src: https://www.javaworld.com/article/3432125/what-is-ejb-the-evolution-of-enterprise-javabeans.html

EJBs are used to implement the **business layer**. In JavaEE application, implementation of different concerns is seperated, such as **persistence layer** (e.g., JPA, JDBC), **presentation layer**, **business layer**, and **interoperability layer(external service, e.g., RESTful)**. **Business layer** models the actions of the application (such as create a book, buy a book, print an order, deliver a book).

## Understanding EJBs

**_EJBs are server-side componenets that encapsulate business logic and take care of transaction and security._** EJBs are used to build business layer that interacts with other components and technologies (JPA, JMS, JTA, JNDI, RMI and so on).

## Types of EJBs

There are two types of EJBs:

- **Session Beans**
- **Message-Driven Beans(MDBs)**

**Session Beans** are greate for implementing business logic, processes, and workflow, they are invoked by clients. **MDBs** are greate for integrating with external systems by receiving and responding to async messages using JMS, and they cannot be invoked by clients.

A **Session Bean** can fall under three categories, each with different features:

- **Stateless**: Session bean contains no conversational state, it's good for tasks done by one single step/method.
- **Stateful**: Session bean contains conversational state, and must be retained across methods for a single user, its' good for tasks that need multiple steps/methods.
- **Singleton**: Session bean that can be shared between clients and supports concurrent access.

Packages:

- javax.ejb
- javax.ejb.embeddable
- javax.ejb.spi

## Writing EJBs

Session beans are **transactional** that mains ACID operations. When marking one to be an EJB, we need to define whether it's stateless, stateful, or singleton.

    @Stateless
    public class BookEJB{

        @PersistenceContext(unitName = "bookDB")
        private EntityManager em;

        public Book findBookById(long id){
            return em.find(Book.class, id);
        }

        public Book createBook(Book book){
            em.persist(book);
            return book;
        }
    }

Above example, the BookEJB is a **stateless** bean, which means it doesn not need to contain any conversational state, it's used for simple, independent, one-step tasks (not dependent on or by others). Retriving a book from DBMS can be considered as a single task, it's not client specific.

## Anatomy of EJBs

EJB has the following anatomy:

- Session bean must be annotated with either @Stateless, @Stateful, @Singleton
- EJB may or may not have the **business interfaces (e.g., local interfaces, remote interfaces)**.

**Business interfaces** are essentially normal interfaces as contracts for the clients to invoke the appropriate methods, one can be **@Remote** for the clients in another JVM to invoke remotely through RMI, or one can be **@Local** (within same JVM) to invoke.

- **@Remote**: Denotes a remote business interfaces, methods parameters are passed by value and serialised as part of the RMI protocol.
- **@Local**: Demotes a local business interfaces.

Example of business interfaces:

    @Local
    public interface ItemLocal{
        List<Book> findBooks();
        List<CD> findCds;
    }

    @Remote
    public interface ItemRemote(){
        List<Book> findBooks();
        List<CD> findCds;
        Book createBook(Book book);
    }

    // EJB implementing these business interfaces
    @Stateless
    public class ItemEJB implements ItemLocal, ItemRemote {...}

In case where the interfaces are not modifiable due to the legacy reason, we can define which is @Local and which is @Remote on the EJB class without modifying the interfaces.

    public interface ItemLocal{
        List<Book> findBooks();
        List<CD> findCds;
        Book createBook(Book book);
        CD createCD(CD cd);
    }

    public interface ItemRemote(){
        List<Book> findBooks();
        List<CD> findCds;
        Book createBook(Book book);
        CD createCD(CD cd);
    }

    // EJB implementing these business interfaces
    @Stateless
    @Remote(ItemRemote.class)
    @Local(ItemLocal.class)
    @LocalBean
    public class ItemEJB implements ItemLocal, ItemRemote {...}

**_When an EJB exposes (implements) a remote view, the EJB should be annotated with @LocalBean if all of its methods also need to be exposed locally, else the EJB will need to implement a local view pointlessly. I.e., with it, we don't need the ItemLocal interface anymore._**

add src: https://stackoverflow.com/questions/10889563/ejb-3-1-localbean-vs-no-annotation/10896403

## Web Services Interface

In addtion to remote interface through RMI, stateless bean can also be invoked remotely as **SOAP** and **RESTful** web services.

    // SOAP web services
    @WebService
    public interface ItemSOAP{
        List<Book> findBooks();
        List<CD> findCds;
        Book createBook(Book book);
        CD createCD(CD cd);
    }

    // RESTful Web services
    @Path("/items")
    public interface ItemREST{
        List<Book> findBooks();
    }

    // EJB
    public class ItemEJB implements ItemSOAP, ItemREST{...}

## Stateless Beans

**_Stateless means a task has to be completed in a single method call._**

An example of how stateless bean is used:

    Book book = new Book();
    book.setTitle("abc");
    book.setPrice(12345F);
    bookService.persistToDB(book);

**_Stateless services are independent, self-contained, and do not require state information from others._** In the above example, book is stateful, while the bookService is stateless. With stateless beans, it also means that the **container can maintain a pool of stateless beans that are used by multiple clients**.

    @Stateless
    public class BookEJB{

        @PersistenceContext(unitName = "bookDB")
        private EntityManager em;

        public Book findBookById(long id){
            return em.find(Book.class, id);
        }

        public Book createBook(Book book){
            em.persist(book);
            return book;
        }
    }

Stateless EJB is also supported by container, including CDI.

## Stateful Beans

Stateful Beans are good for tasks that take multiple steps, where the state of previous step needs to be preserved in order to finish the tasks.

An example of how stateful bean is used:

    Book bookOne = new Book("....");
    Book bookTwo = new Book("....");
    statefulBasket.addBookToCart(bookOne);
    statefulBasket.addBookToCart(bookTwo);
    statefulBasket.checkoutCart();

In this example, bookOne and bookTwo are stateful beans that preserve the details of a Book. The statefulBasket bean is also stateful, in that it preserves state of previously added book in the cart. This whole task/operation takes multiple steps, thus the EJB needs to be stateful until the end points, e.g., session ends or checkout operation. **_Stateful beans are client specific_**.

More specifically, a stateful bean can be as follows:

    @Stateful
    @StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
    public class ShoppingCartEJB{

        private List<Item> items = new ArrayList<>();

        public void addItem(Item item){
            if(!items.contains(item))
                items.add(item);
        }

        public void removeItem(Item item){
            if(items.contains(item))
                items.remove(item);
        }

        public int getNumOfItems(){
            if(items == null || items.isEmpty())
                return 0;
            else
                return items.size();
        }

        public void clear(){
            if(items != null){
                items.clear();
            }
        }

        @Remove
        public void logOut(){
            // clean up some sources or do some business logic before this bean being destroyed
        }
    }

In this example, this EJB is marked as **@Stateful**, a **@StatefulTimeout** is manually assigned for the session timeout, howev er, it can be omitted and rely on container instead. **@Remove** is similar to **@PreDestroy** that it tells the container to destroy this bean permanently, and provides some flexibility to close up some resources or perform extra business logic.

## Singletons

**_A singleton bean is a session bean that is instantiated once per application._** Thus, it's shared among the clients. **@Singleton** is all needed to mark an EJB as singleton. No need to worry about private constructor and or method to get the instance.

    @Singleton
    public class CacheEJB{

        private Map<Long, Object> objectCache = new HashMap<>();

        public Object getFromCache(long key){
            if(objectCache.containsKey(key))
                return objectCache.get(key);
            else
                return null;
        }

        public void removeFromCache(long key){
            if(objectCache.containsKey(key))
                objectCache.remove(key);
        }

        public void addToCache(long key, Object object){
            if(!objectCache.containsKey(key))
                objectCache.put(key, object);
        }
    }

### Singleton Initialisation

Instantialising a singleton object can be time-consuming especially for the above use case, we may ask the container to create it at startup rather then when one needs to access it. We use **@Startup** annotation.

    @Singleton
    @Startup
    public class CacheEJB{...}

### Singleton Chaining

In some cases, there are multiple singletons, and one depends on another. Thus, there is order when initialising these singletons. We need **@DependsOn**.

    @Singleton
    public class CountryCodeEJB{...}

    @Singleton
    public class PostcodeEJB{...}

    @Singleton
    @DependsOn("CountryCodeEJB", "PostcodeEJB")
    @Startup
    public class CacheEJB{...}

In this case, CacheEJB is initialised during startup, however, it depends on CountryCodeEJB and PostcodeEJB, thus the two dependencies are initialised first before CacheEJB.

When a dependent singleton is packaged in jar file, we can still initialise it as dependency. The format is **some.jar#SomeEJBName**.

    @Singleton
    @DependsOn("business.jar#BusinessCodeEJB")
    @Startup
    public class CacheEJB{...}

### Singleton - Concurrency Problem

In case where the singleton is a mutable object, the concurrent access to the singleton is allowed and should be managed. There are two types of Concurrency Management using **@ConcurrencyManagement**:

- **Container-managed concurrency (CMC)**: concurrency managed by container based on metadata (annotation or XML).
- **Bean-managed concurrency (BMC)**: full concurrent access is allowed, and the synchronisation responsibility is deferred to the bean.

When no **@ConcurrencyManagement** annotated, CMC is used by default.

#### Container-Managed Concurrency

A mechanism similar to Read-write lock. Two annotations are:

- **@Lock(LockType.WRITE)**: exclusive lock, only one can access at the time.
- **@Lock(LockType.READ)**: shared lock.

These two can be applied on **class-level** and **method-level** similar to scopes in CDI.

    @Singleton
    @Lock(LockType.WRITE)
    public class CacheEJB{

        //...

        @Lock(LockType.READ)
        public Object getFromCache(long key){
            if(objectCache.containsKey(key))
                return objectCache.get(key);
            else
                return null;
        }

        public void addToCache(long key, Object object){
            if(!objectCache.containsKey(key))
                objectCache.put(key, object);
        }
    }

#### Bean-Managed Concurrency

BMC defers the responsibility to the singleton itself. We may use JavaSE synchronisation to manage the concurrency problem. To use BMC, we specifies **@ConcurrencyManagement(ConcurrencyManagementType.BEAN)**.

    @Singleton
    @ConcurrencyManagement(ConcurrencyManagementType.BEAN)
    public class CacheEJB{

        //...

        public synchronized void addToCache(...){...}

        public synchronized void removeFromCache(...){...}
    }

#### Concurrent Access Timeouts and Not Allowing Concurrency

When a thread fails to acquire the lock, it is blocked until the lock is acquired. **@AccessTimeout** is used to specify the duration that the attempt should be blocked, a value of -1 means that the request will be blocked indefinitely, and a value of 0 means that the current access is not allowed at all where an ConcurrentAccessException is thrown.

    @Singleton
    public class CacheEJB{

        @AccessTimeout(0)
        public synchronized void addToCache(...){...}
    }

In this example, the concurrent access to _addToCache()_ method is blocked, an exception is thrown when concurrent acess happens. A client may handle the exception and tries again.

## Dependency Injection

There is an annotation **@EJB** specific for injecting EJB. However, with **@Inject**, we can inject almost everything **including the EJB**.

    @Stateless
    public class ItemEJB{

        @EJB
        private CustomerEJB customerEJB;
    }

## Session Context

**SessionContext interface** allows programmatic access to the runtime session context.

    javax.ejb.SessionContext

For example, we can use SessionContext to check the security role of the session instance. Roles are defined in the deployment descriptor.

    @Stateless
    public class ItemEJB{

        //...

        @Resource
        private SessionContext context;

        public Book createBook(Book book){
            if(!context.isCallerInRole("admin"))
                throw new SecurityException("Only admins can create books");

            em.persist(book);
        }
    }

In above example, we use **@Resource** to inject the SessionContext for the current session instance (the client). We uses the **.isCallerInRole()** method to check the security role of the session instance.

---

**_Usage and purpose of Other methods of SessionContext are still unknown._**

---

## Asynchronous Calls

**_By default, invocations of methods on the business interfaces of a session bean are synchronous._** It means that when a client invokes a method, it gets blocked until the method is finished.

It is commmon that tasks are required to be asynchronous, especially the long-running tasks. In such case, we only need to annnotate a method with **@Asynchronous**, then the invocation of the method will not block the client for following operations.

    @Stateless
    public class OrderEJB{

        @Asynchronous
        public void sendEmailOrderComplete(Order order, String email){
            // time-consuming task
        }

        @Asynchronous
        public Future<PdfFile> printOrder(Order order){
            // time-consuming task
            return new AsyncResult<>(pdfFile);
        }
    }

add src: http://tutorials.jenkov.com/java-util-concurrent/java-future.html

Async calls can either return **void** or **java.util.concurrent.Future\<V>**. The generic **V** refers to the return type, and we must return a type of **AsyncResult**, since **_the result is returned to the container not the caller_**. **_AsyncResult is an implementation of Future interface_**. We can get the result as follows:

    Future<PdfFile> future = orderEJB.printOrder(order);

    // do something else while the async call is processed

    if(future.isDone())
        PdfFile pdfFile = future.get();
    else{
        // check if it's done later
    }

We can also cancle the task as follows:

    Future<PdfFile> future = orderEJB.printOrder(order);
    if(!future.isDone())
        future.cancel(true);

**@Asynchronous** can also be applied at the class level, where all methods become asynchronous.

## Deployment Descriptor

Because of Configuration by Exception, deployement descriptor can be used for configuring the EJB. However, it can end up very verbose. The XML file is **ejb-jar.xml**. If it's deployed in **jar**, it should be at **META-INF/ejb-jar.xml**, else if it's deployed in **war**, it should be at **WEB-INF/ejb-jar.xml**.

## Environment Naming Context

However, one thing good about deployment descriptor is that it allows **stroing the value in the deployment descriptor so that the value is not actually hard-coded**. It is especially good for internationalisation.

    @Stateless
    public class ItemEJB{

        public Item convertPrice(Item item){
            float rate = 0.80F;
            item.setPrice(item.getPrice() * rate);
            return item;
        }
    }

In this case, the rate value is hard-coded to be 0.8F. However, if the currency is different in different countries, this must be changed as well. One way to do it is through deployment descriptor.

We first stroe it in the deploymnet descriptor:

    <ejb-jar xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
    http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd"
    version="3.2">
        <enterprise-beans>
            <session>
                <ejb-name>ItemEJB</ejb-name>
                <env-entry>
                    <env-entry-name>currencyEntry</env-entry-name>
                    <env-entry-type>java.lang.String</env-entry-type>
                    <env-entry-value>Euros</env-entry-value>
                </env-entry>
                    <env-entry>
                    <env-entry-name>changeRateEntry</env-entry-name>
                    <env-entry-type>java.lang.Float</env-entry-type>
                    <env-entry-value>0.80</env-entry-value>
                </env-entry>
            </session>
        </enterprise-beans>
    </ejb-jar>

Here, we store two values, one is currencyEntry: **String** which has a value "Euros", another is changeRateEntry: **Float** which has a value of 0.80. We then can **inject these values using @Resource**.

    @Stateless
    public class ItemEJB{

        @Resource(name = "currencyEntry")
        private String currency;

        @Resource(name = "changeRateEntry")
        private float rate;

        public Item convertPrice(Item item){
            item.setPrice(item.getPrice() * rate);
            return item;
        }
    }

## Testing With Embeddable API

    javax.ejb.embeddable

**EJB** requires a container to run, thus testing them is hard. The Embeddable API provides a way to execute EJBs in a JavaSE environment for testing.

    public static void main(String[] args) throws NamingException{

        // sets container classpath
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));

        // create embedded container and get JNDI context
        try(EJBContainer container = EJBContainer.createEJBContainer(properties);){

            // JNDI context (javax.naming.Context)
            Context context = container.getContext();

            // POJO
            Book book = new Book(...);

            // EJB
            ItemEJB itemEJB = (ItemEJB) context.lookup("java:global/classes/ItemEJB");
            itemEJB.createBook(book);
        }
    }

## Invoking EJBs

Client can access to and invokes their EJBs through dependency injection (**@EJB** or **Inject**). EJB may be invoked by any kind of componenets: POJO, graphical interface, CDI managed bean, servlet, JSF-backing bean, SOAP, RESTful or another EJB (locally or remotely).

Injecting an EJB is generally the same:

    // in client code
    @EJB
    ItemEJB itemEJB;

    // do something with the EJB

In this case, ItemEJB is the concrete class of the EJB. However, if it has **business interfaces**, e.g., remote one or local one or no view at all. Injection must explicitly declare the interface to be injected (i.e., in a strong typing way). Say, it implements a **@Remote** interface called ItemRemote, the client will then inject this interface, and uses the methods on this interface instead.

    // in client code
    @EJB
    ItemRemote itemEJBRemote;

    // do something with the methods on this remote business interface.

**_EJB injection also works with CDI_**.

    @Inject ItemEJB itemEJB:
    @Inject ItemRemote itemEJBRemote;

For remote EJBs, we can use JNDI to look it up, produce it and inject it.

    // producer
    @Produces
    @EJB(lookup = "java:global/classes/ItemEJB")
    ItemRemote itemEJBRemote;

    // in client code
    @Inject ItemRemote itemEJBRemote;
