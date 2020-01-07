# Chapter 2 - Context and Dependency Injection

CDI follows the concept of **Inversion of Control(IoC)**, which means that **container would take control of your business code and provide necessary services**. CDI is also built based on the concept of **Losse Coupling, Strong Typing**, such that beans are injected and managed in a way that the beans are loosely coupled but strongly typed.

---

## Beans

In JavaSE, there are **JavaBeans**. In JavaEE, there are **Enterprise JavaBeans(EJB)**. POJOs are just Java classes that run inside JVM. JavaBeans are POJOs that follow certain patterns:

- naming convention for getter and setters
- default constructor
- and so on

In JavaEE, EJB also follows certain patterns, for example:

- EJB must have metadata (annotations)
- default constructor cannot be final

**Managed Beans** are container-managed onbjects that support only a small set of basic services, e.g., resource injection, life-cycle management, and interception. EJB can be considered as Managed Beans with extra services.

Essentially, **Beans are CDI objects**, which have an improved life cycle for stateful objects; can be bound to well-defined contexts; can be injected, intercepted and decorated; and can be used in Expression Language. With very few exceptions, most of the Java classes can be a bean that takes advantage of the CDI services.

---

## Dependency Injection

**_Dependency Injection is a design pattern that decouples dependent components._** It is a part of Inverion of Control, in that the process of obtaining dependency is reverted. Instead a bean looking to handle its dependency (e.g., other beans), the container injects these dependent beans for it.

---

## Life-Cycle Management

Life cycle of a POJO is simply that, a object being instantiated, used, and collected by Garbage Collector. However, with CDI, a bean is not instantiated using "new" keyword, instead, it is injected using annotation and thus handled by the container.

Generally, a Managed Bean or CDI Bean is handled by the container as follows:

1. Container creates the instanece (bean)
2. Resolves dependencies for this bean
3. Invokes any method annotated with **@PostConstruct**, before invoking first business methods
4. Invokes business methods
5. @PreDestroy Callback notifications signal before it is removed by the container
6. Bean destroyed by container

---

## Scopes and Context

CDI Beans may or may not be **stateful** and are **contextual**. I.e., CDI beans are living in a defined **Scope**. This is also true for the injected beans, or i.e., the entire chain of the bean dependencies is contextual. As such, the whole chain of dependencies is managed by the container.

**Scopes**:

1. Request Scope
2. Session Scope
3. Appication Scope
4. Conversation Scope

Unlike the **stateless componenets** or **singletons**, which are stateless; the stateful beans are dependent on the context, for example, sessions that the beans are in. In different context, beans will be of different states, and are all handled by the container according to the scope.

---

## Interception

**Interceptors** are used to interpose on business method invocations. This is similar to **Aspect-Oriented Programming**, which aims to separates the **cross-cutting concerns** from the business code. Applications may have operations that are repeated across componenets, either technical concern (authorisation) or business concern.

Interceptors are automatically triggered by the container when a Managed Bean method is invoked. **Intercepts are chained and are call before/after the execution of a method of a bean (similar to filter for servlets).**

Essentially, Interceptors are defined classes that undertake certain operations before or after an invocation of a bean's methods. Develoeprs only need to focus on implementation of business methods and interceptors, the invocation of interceptors will be handled by the container.

---

## Loose Coupling and Strong Typing

Interceptors are a powerful way to decouple technical concerns from business logic. CDI also abstract away the concrete implementation of any bean the a bean dependent on, while which is delivered in a typesave manner.

---

## Deployment Descriptor

With CDI, a deployment descriptor **beans.xml (in META-INF or WEB-INF)** is manadatory, that is used to configure such as interceptors, decorators, alternatives and so on. Without any extra configuration, it is still needed to enable **bean discovery** in the class path.

---

## Reference Implementation

CDI is implemented by **Weld** from JBoss. Other reference implementation includes such as **Apache OpenWebBeans** and **CanDi from Caucho**.

# CDI Bean

A CDI Bean can be any kind of class that contains business logic. It may be injected or invoked via Expression Language. A Bean is a POJO that doesn't inherit or extend from anything.

    // a CDI Bean
    public class BookService{

        // Inject dependency
        @Inject
        private NumberGenerator numberGenerator;

        private Date createdDate;

        // called by container after the object instantiation
        @PostConstruct
        private void createDate(){
            createdDate = new Date();
        }
    }

---

## Anatomy of a CDI Bean

Based on CDI 1.1 Specification, a class is a CDI Bean that satisfies following conditions:

- is not a non-static inner class
- is concrete class, or is annotated @Decorator
- has default constructor, or it declares a constructor annotated @Inject

A bean can have an **optional scope**, **optional EL name**, **a set of interceptor bindings**, and an **optional life-cycle management**.

## Dependency Injection

With CDI and **@Inject** annotation, nearly anything can be injected into any beans. Other mechanisms such as @Resource can still be used (that is introduced prior to JavaEE 7), but @Inject should be prefered.

    For example:

    @Inject
    private NumberGenerator numberGenerator;

NumberGenerator can be an interface or a concrete class. It doesn't matter, though if it's an interface, concrete implementation should be provided in the class path.

### Injection Point

**Injection Point** refers to the point where a dependency is injected, or i.e., where @Inject is used. Injection can occur via **property**, **setter**, and **constructor**. While using injection via constructor, **there sould only be one constructor injection point** to avoid confusion by the container.

For Injection via property:

    @Inject
    private NumberGenerator numberGenerator;

For Injection via constructor:

    @Inject
    public BookService(NumberGenerator numGen){
        this.numberGenerator = numGen;
    }

For Injection via setter:

    @Inject
    public void setNumberGenerator(NumberGenerator numGen){
        this.numberGenerator = numGen;
    }

### Default Injection

When attemptting to inject an interface object, there can be one or more concrete implementation of an interface. If there is only one concrete implementation, container will select the one to inject with only @Inject annotation. This is called **Default Injection**.

When only the @Inject annotation is provided without declaring qualifiers, container will inject the one with **@Default** qualifier. However, if there is only concrete implementation, with or without @Default is the same.

    E.g.,
    // these two are the same when there is only one implementation
    @Inject
    private NumberGenerator numberGenerator;

    @Inject @Default
    private NumberGenerator numberGenerator;

We can use **@Default** qualifier to mark one concrete implementation class as the default implementation.

    E.g.,

    @Default
    public class IsbnGenerator implements NumberGenerator{
        ...
    }

When there are two or more concrete implementations, we will need **Qualifiers** to specify how implementations are chosen.

---

## Qualifier

**Qualifier** is used for the system to determine which implementation or dependency to inject for each bean during the system initialisation time. Without additional qualifier used, system will look for the only concrete implementation or the one with @Default qualifier as the needed dependency. However, if ambiguous dependencies or implementation exist, additional qualifiers are needed to clarify which dependency should be injected.

    @Inject
    private NumberGenerator numberGenerator;

In the example above (**BookService**), an Bean **NumberGenerator** is injected into BookService Bean. Here, in this example, _NumberGenerator_ is an interface implemented by _IsbnGenerator_ class and _IssnGenerator_ class. When there is only one concrete implementation, the container is smart enough to find the right implementation to inject. However, when there are two or more concrete implementation, addition configuration is needed using qualifiers ("custom" annotations).

Qualifier is a **user-defined annotation**, the Qualifier itself is annotated with **@javax.inject.Qualifier**.

For example, we can implement Qualifiers as follows, we create two Qualifiers, one is called _"@Isbn"_, and another is called _"@Issn"_

    @Qualifier
    @Retention(RUNTIME)
    @Target({FIELD, TYPE, METHOD})
    public @interface Isbn{}

    @Qualifier
    @Retention(RUNTIME)
    @Target({FIELD, TYPE, METHOD})
    public @interface Issn{}

Then we can use these two Qualifiers to tell the system, which dependency should be injected. To them, we **annotate the implementation class with appropriate Qualifier, which represents them in forms of decoration**.

    @Isbn
    public class IsbnGenerator implements NumberGenerator{
        ....
    }

    @Issn
    public class IssnGenerator implements NumberGenerator{
        ....
    }

Now, **these two Qualifiers will represent the two concrete class**, so that we can inject them in such a way that the beans are loosely coupled and strongly typed. If we want to inject the _NumberGenerator bean_ with the _IsbnGenerator implementation_, we can do this as follows:

    @Inject @Isbn
    private NumberGenerator numberGenerator;

For the IssnGenerator implementation:

    @Inject @Issn
    private NumberGenerator numberGenerator;

### Qualifiers with Members

By using Qualifiers, it becomes easier to handle dependency injection when there is different implementation available. However, **this can become redundant when we need to create a qualifier evertime we create an implementation**. We can use **members** to avoid creating too many Qualifiers. **Members** are essentially the values or properties of the **Qualifiers/Annocations**.

    e.g.,

    @Qualifier
    @Retention(RUNTIME)
    @Tartget({FIELD, TYPE, METHOD})
    public @interface NumberOfDigits{

        // members
        Digits value();
        boolean isOdd();
    }

    public enum Digits{
        ONE,
        TWO,
        EIGHT,
        TEN,
        THEIRTEEN
    }

We create a Qualifier called _NumberOfDigits_ to determine which implementation of _NumberGenerator_ should be injected. In it, there are two **members**, _value_ and _isOdd_. Then, **_with members, instead of creating a new Qualifier everytime we create an implementation, we configure the Qualifier at the implementation class and the injection point_**.

    e.g.,
    // for the implementation class
    @NumberOfDigits(value = Digits.ONE, isOdd = false)
    public class IsbnGenerator implements NumberGenerator{...}

    // for the injection point
    @Inject @NumberOfDigits(value = Digits.ONE, isOdd = false)
    private NumberGenerator numberGenerator;

### Multiple Qaulifiers

Another approach to qualify a bean (concrete implementation) is to **specify multiple qualifiers**. Instead of using a qualifier with members, we use multiple qualifiers to determine which bean to inject.

    e.g.,
    // for the implementation class
    @ThirteenDigits @Even
    public class IsbnGenerator implements NumberGenerator{...}

    // for the injection point
    @Inject @ThirteenDigits @Even
    private NumberGenerator numberGenerator;

---

## Alternative

**@Alternative (javax.enterprise.inject.Alternative)** Qualifier can be used to specify which implementation to be injected for testing. This can be used for **mock test** in a testing environment. This is by default disabled and needs to be enabled in **beans.xml**. **When enabled, nothing needs to change in the injection point, the one with @Alternative will be injected automatically.**

    // In beans.xml:

    <beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
        http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        version="1.1" bean-discovery-mode="all">

        <alternatives>
            <class>com.curtisnewbie.MockGenerator</class>
        </alternatives>
    </beans>

    // for the implementation class:
    @Alternative
    public class MockGenerator implements NumberGenerator{...}

    // for the Injection point:
    @Inject
    private NumberGenerator numberGenerator;

---

## Producers

additional src: https://stackoverflow.com/questions/16534728/please-explain-the-produces-annotation-in-cdi <br>
additional src: https://antoniogoncalves.org/2011/09/25/injection-with-cdi-part-iii/ <br>
additional src: https://stackoverflow.com/questions/23615224/two-producers-in-the-same-bean-cause-ambiguity-error

**Producers are essentially a way to obtain a bean that is not injectable or requires some custom configuration for inistantiation.** It literally means **to produce a bean**. With **@Produces**, we can even inject primitive data as well. A bean is not injectable, when it doesn not have a default constructor or there is no beans.xml in its class path. So, using Producers can be for injecting the external libraries that do not support CDI (not containing beans.xml for example).

We **create Qualifier to represent the produced beans**, so whenever the qualifier is used in conjunction with **@Inject**, the appropriate beans are created and injected. This is optional. When no Qualifier is provided with @Produces, the Injection is bounded to the Type of the produced object. For example, if the produced bean is a String, there should only be one String with no qualifier to avoid ambiguity. We then **define a way to create a bean that we want to inject through methods or field**, and inject the produced beans when necessary.

To use it, we create qualifier representing the produced beans:

    // create a Qualifier for the produced bean
    @Qualifier
    @Retention(RUNTIME)
    @Target({FIELD, TYPE, METHOD})
    public @interface @RandomNumber{}

We then define a way to create a bean that we want to inject (methods or field):

    // produce bean through methods:
    @Produces @RandomNumber
    public double createRandomNumber(){
        return Math.abs(new Random().nextInt());
    }

    // to inject a random number
    @Inject @RandomNumber
    private double randomNum;

We create a qualifier as the reference to the produced bean. So when we want to inject this bean, we using this qualifier. As shown above, _@RandomNumber_ qualifier refers to the bean that is created by _createRandomNumber() method_, when we want to inject a random double number, we use _@Inject @RandomNumber_; the container will know how to create this bean and inject the bean when needed.

**Producer also supports primitive data type**.

    // create two qualifiers for produced beans
    @Qualifier
    @Retention(RUNTIME)
    @Target({FIELD, TYPE, METHOD})
    public @interface @Friend{}

    @Qualifier
    @Retention(RUNTIME)
    @Target({FIELD, TYPE, METHOD})
    public @interface @Family{}

    // some random class that contains ways to produce beans, they are discovered by the container automatically
    public class RelationProducer{

        @Produces @Friend
        private String friend = "We are friend";

        @Produces @Family
        private String family = "We are family";
    }

    // we can then inject these two strings and use them directly
    @Inject @Friend
    private String relation;

    // or
    @Inject @Family
    private String relation;

Again, we create two qualifiers (_@Friend and @Family_) representing the two strings (_beans_). We use @Produces to annotate them as produced beands, and use qualifiers to give them a "name". When we want to inject these two strings, we use _@Inject_ and their associated qualifier when needed. **This is not limited to String, it can be double, or other data types and object.**

---

## InjectionPoint API

There are certain cases where we need to know about the **information of the injection point**, through which the behaviour is configured and changed. For example, to create a logger for the class, we need to pass the class name to it. We can simply create a Logger using **Producer**:

    @Produces
    private Logger createLogger(){
        return Logger.getLogger("ClassName");
    }

However, with this producer, we do not know the know of the class. We need to use the **InjectionPoint API** to gain the information of the injection point when the container _produces_ this bean and _inject_ this bean. To use **InjectionPoint API**:

    @Produces
    private Logger createLogger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

With the InjectionPoint methods, the logger produced will be of the name of the class. Then, we only need to inject it normally:

    @Inject
    Logger logger;

---

## Disposers

**Disposer** used to destroy or close the beans created by **Producer**, which allows **customised methods** to perform cleanup of an object that is returned by a producer method.

**_Each disposer method, annotated with @Disposes, must have exactly one disposed parameter of the same type (here java.sql.Connection) and qualifiers as the corresponding producer method return type (annotated @Produces). This is to avoid ambiguity._**

**_Disposer method is called automatically when the scope and context ends to handle the beans._**

    e.g,

    @Produces
    private Connection createDBConn(){
        ...
        return connection;
    }


    @ApplicationScoped
    public class JDBCService(){

        @Inject
        private Connection conn;

        private void closeConnection(@Disposes Connection conn) throws SQLException{
            conn.close();
        }
    }

In this example, when **application is closed (Application Scope)**, the Connection bean produced by the **producer** above is closed by calling the **dispose method - closeConnection() method**.

**_Generally, Producers and Disposers are the way to create and close beans._**

---

## Scopes

In JavaEE, every objects should be managed by CDI based on a well-defined **scope** and **life cycle** that is bound to a specific **context**. A CDI bean lives in a context and it remains in that context until the bean is destroyed by the container. Scope defines when should the container creates and destroyes those beans.

CDI defines following scopes:

1. Application Scope (**@ApplicationScoped**): Entire duration of an applicaition from start to end.
2. Session Scope (**@SessionScoped**): Within a session.
3. Request Scope (**@RequestScoped**): Within a single HTTP request.
4. Conversation Scope (**@ConversationScoped**): Within a specified starting point and end point determined by the application. This is usually shorter then session, which involves a specific multistep workflow.
5. Dependent pseudo-scope (**@Dependent**): Lifecycle of a bean dependent on the client (e.g., session, request or etc). This is the default scope for CDI.

.

    [all scopes are under javax.enterprise.context]

.

**_Beans with scope @SessionScoped or @ConversationScoped must be Serializable, as container pasivates over a period of time._**

If scope is not explicitly specified, beans belong to the **@Dependent** scope. **With this scope, beans are never shared between different clients or different injection points, they are always dependent on the other beans and are bound to the lifecycle of other beans.**

For **@SessionScoped**, sessions end after a defined period of time (say 30 mins). For **@RequestScoped**, scope ends for one single HTTP request. When scope starts, container creates beans; when scope ends, beans destroyed. However, **@ConversationScoped** is slightly different, it spans across **the programmatically defined starting and ending points**.

### Conversation Scope

**@ConversationScoped is democrated programmatically by the application.** There is a definite beginning and end. A **Conversation bean must be injected** to define the conversation scope, and **the beginning and end are defined by conversation.begin(); and conversation.end()**.

For example, think of a Customer Registration WebApp, where there are three basic steps required to register a new customer: 1) enter log in info (name, password), 2) enter account details, 3) create account. A conversation scope can be programmatically defined spanning across these three steps.

    @ConversationScoped
    public class CustomerRegistration implements Serializable {

        @Inject
        private Conversation conversation;

        public void saveLoginInfo(){
            // begin conversation scope
            conversation.begin();

            ...
        }

        public void saveAccountDetail(...){
            ...
        }

        public void createCustomer(){
            ....

            // end conversation scope
            conversation.end();
        }
    }

In this Conversation scope, it spans across the three steps which may involve multiple requests.

## Interceptors

**Interceptors allow adding cross-cutting operations to the beans**. Generally, when a client invokes a method on a **Managed Bean** such as a _RESTful web service_, the container will **intercept the call and process defined operations before the bean's method is invoked**. After certain operations are done in Interceptors, **invocationContext.proceed()** is called to continue the interceptor chain or invoke the business method, See example below.

Interceptors fal into four types:

1. Constructor-level interceptors (**@AroundConstruct**): intercept the constructor
2. Method-level interceptors (**@AroundInvoke**): intercept specific methods
3. Timeout method interceptors (**@AroundTimeout**): intercept timeout methods for only EJB timer services.
4. Life-cycle callback interceptors(**@PostConstruct and @PreDestroy**): intercept targeted instance life-cycle event callbacks.

### Target Class Interceptors

A method democrated with interceptors annotations will be called when needed based on its level. For example, a method-level interceptors (marked with @AroundInvoke), will be invoked whenever a method on a managed bean is invoked. This is called **Target Class Interceptors**, as the interceptors are declared within the targeted class.

    public class CustomerService{

        public void createCustomer(){
            ...
        }

        public void deleteCustomer(){
            ...
        }

        public void findCustomer(){
            ...
        }

        @AroundInvoke
        private Object logCall(InvocationContext invocationContext) throws Exception{
            // log, before business method invoked

            // perform busines methods
            invocationContext.proceed();

            // log, after business method invoked
        }
    }

In above examples, there are three business methods. The logCall() method is annocated with **@AroundInvoke** is invoked whenever the three business methods are invoked, since it's a method-level interceptors. **invocationContext.proceed()** is called to invoke the intercepted business methods, without calling this method will prevent the invocation of the business method.

**A interceptors must follow following signature pattern**:

    [modifier] [Non static/final] Object [methodName] (InvocationContext ic) throws Exception{...}

General rules for **Method-Level Interceptor** methods (some are also applied to other interceptors):

1. Can have public, private, protected or package level, but must not be static or final
2. Must have InvocationContext as parameter
3. Must return an Object
4. Can throw an Exception

**_InvocationContext object allows interceptors to control the behaviour of the invocation chain. If several interceptors are chained, the same InvocationContext object is passed to each interceptor._**

With the InvocationContext, we can have access to some of the **contextual data** while the beans' methods are called and intercepted. For example, _invocationContext.getMethod()_ to get the method being invoked, _invocationContext.getTarget()_ to get the bean that the interceptor belongs to.

### Class Interceptors

The above example defines Interceptor in CustomerService class, but most of the time, the **Interceptors are isolated into a seperate class**. This is called **Class Interceptor**.

    public class LoggingInterceptor{

        @Inject
        private Logger logger;

        @AroundConstruct
        private void init(InvocationContext ic) throws Excpetion{
            //log something, constructor called

            ic.proceed();

            // log something, constructor exited
        }

        @AroundInvoke
        public Object logMethod(InvocationContext ic) throws Exception{
            // log, method invoked

            ic.proceed();

            // log, method existed
        }
    }

In this example, a **Class Interceptor** is created, that can be used in multiple, different beans. _init(InvocationContext ic) method_ is democrated with _@AroundConstruct_ annocation that it will only intercept when the constructor of the targeted bean is invoked. _logMethod(InvocationContext ic) method_ is democrated with _@AroundInvoke_ annotation that it will only intercept when the business methods of the targeted bean is invoked, however, this can be applied to specific level or to the whole class.

We can use this class interceptor as follows:

    public class CustomerService{

        @Inject
        private EntityManager em;

        @Interceptors(LoggingInterceptor.class)
        public void createCustomer(Customer customer){
            ...
        }

        public Customer findCustomer(...){
            ...
        }
    }

**Interceptor can be attached to a class or a method using the annotation - @Interceptors(InterceptorClass.class)**. In this example, this class interceptor is only attached to the _createCustomer()_ method, thus only this method is intercepted by calling the _logMethod(InvocationContext ic)_. This also means the method _findCustomer()_ is not intercepted, since it doesn't have the annocation. **As the interceptor has a method declared with @AroundConstruct, even though it's only attached on one of the method, the constructor is also intercepted.**

To apply the **class interceptor for the whole class, apply the annotation to the class.**

    @Interceptors(LoggingInterceptor.class)
    public class CustomerService{

        @Inject
        private EntityManager em;

        public void createCustomer(Customer customer){
            ...
        }

        public Customer findCustomer(...){
            ...
        }
    }

Then the whole class will be intercepted based on the methods (**annoated with @AroundInvoke**) defined in the Interceptor. **To exclued a specific method from being intercepted, use @ExcludeClassInterceptors**.

    @Interceptors(LoggingInterceptor.class)
    public class CustomerService{

        @Inject
        private EntityManager em;

        public void createCustomer(Customer customer){
            ...
        }

        @ExcludeClassInterceptors
        public Customer findCustomer(...){
            ...
        }
    }

### Life-Cycle Interceptor

Life-cycle interceptors include intercepting the life-cycle methods annotated with **@PostConstruct** and **@PreDestroy**. Recall that methods annotated with @PostConstruct and @PreDestroy are not specific to Interceptor, these are life-cycle callback methods of a bean. With these two annotations, in Interceptor, we can declare how we want to intercept beans lifecycle callback methods.

    for example, for a normal managed bean:

    public class CustomerService{

        @PostConstruct
        public void init(){
            // initialise something after the constructor being invoked
        }

        @PreDestroy
        public void close(){
            // close resources or connections before destruction
        }
    }

This bean itself has declared the two lifecycle callback methods to initialise and close its resources. We can declare Interceptors to intercept these two lifecycle methods.

    public class LifeCycleInterceptor{
        @Inject
        private Logger logger;

        @PostConstruct
        public void logStart(InvocationContext ic) throws Exception{
            //log, object is created
            ic.proceed();
        }

        @PreDestroy
        public void logEnd(InvocationContext ic) throws Exception{
            //log, object is about to be destroyed
            ic.proceed();
        }
    }

Then, all we need to do is to annotate the targeted class with this interceptor. Interceptor methods will be automatically mapped to the associated lifecycle methods.

    @Interceptors(LifeCycleInterceptor.class)
    public class CustomerService{...}

### Chaining Interceptors

With **@Interceptors()** annotation, a bean can be attached with mutliple Interceptors. **_The order of Interceptors listed in this annotation determines the order of chaining_**.

    @Interceptors({InterceptorOne.class, InterceptorTwo.class})
    public class CustomerService{

        @Interceptors({InterceptorOne.class, InterceptorTwo.class})
        public void doSomething(){...}

        @ExcludeClassInterceptors
        public void doNotInterceptMe(){...}
    }

In this example, Interceptors are chained in such an order for both class and method _doSomething()_. However, _doNotInterceptMe()_ is not intercepted at all.

    InterceptorOne -> InterceptorTwo -> invoke doSomething() method

### CDI - Interceptor Binding

Interceptors are defined in its own specification, which does not provide the **Interceptor Binding**. CDI extends the specification by introducing the **Interceptor Binding** which aims to make the use of Interceptor to be loosely coupled.

Without binding, to use an interceptor, we need to explicitly annotate the implementation class using **@Interceptors()**. This is typesafe, but not loosely coupled. **_CDI allows creating an Annotation similar to qualifier to represents an Interceptor._** However, CDI must be enabled to use it.

First of all, create an Annotation/ **@InterceptorBinding**:

    @InterceptorBinding
    @Target({METHOD,TYPE})
    @Retention(RUNTIME)
    public @interface Loggable{ }

Then, we can use this **Interceptor Binding to represent an interceptor**, we also need an additional, singular annotation (**@Interceptor**)for the Interceptor:

    @Interceptor
    @Loggable
    public class LoggingInterceptor{
        ....
    }

Finally, we **Iapply this interceptor Binding to the targeted bean at Class level or method level**:

    @Loggable
    public class CustomerService{

        @Loggable
        public void createCustomer(){
            ...
        }
    }

### Prioritise Interceptors Binding

With **Interceptor Binding**, we cannot prioritise Interceptors or determine the order to interceptor chain explicitly. Instead, we can **provide a priority ( @Priority() ) value to each Interceptor, the less the more prioritised**.

    @Interceptor
    @Loggable
    @Priority(200)
    public class LoggingInterceptor{
        ....
    }

### Enable Interceptors In Beans.xml

Similar to Alternative, Interceptors are disabled by default. This should be enabled as follows:

    <beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
        http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        version="1.1" bean-discovery-mode="all">
        <interceptors>
            <class>com.curtisnewbie.LoggingInterceptor</class>
        </interceptors>
    </beans>

---

## Decorators

**Decorators** are one of common design pattern from GoF. The general idea is that the Decorators is **a class that wraps another class around it (decorate it) to provide additional functionalities.** In essence, it may still using the implementation of original objects, except that it provide some additional functionalities on top of that original implementation at runtime. It is quite similar to **Interceptor** in terms of their use.

To use Decorator, the Decorator must be annotated with **@Decorator**, the **wrapped implemenation/bean** will need to be injected using **@Inject** and **@Delegate**.

    @Decorator
    public class SuperNumGenDecorator implements NumberGenerator{

        @Inject
        @Delegate
        private NumberGenerator numberGenerator;

        public String generateNumber(){
            return "Extension Functionality::: " + numberGenerator.generateNumber();
        }
    }

_SuperNumGenDecorator_ is a Decorator that wraps the orginal concrete implementation, which is the injected bean _numberGenerator_. The Decorator itself is also a NumberGenerator, it implements the method _generateNumber()_, and add additional functionalities on top of the wrapped bean.

**This decorator class can be abstract, so that it doesn't need to implement every methods specified by the interface.**

**_Decorator is disabled by default, it needs to be enabled in beans.xml_**

    <beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
        http://xmlns.jcp.org/xml/ns/javaee/beans_1_1.xsd"
        version="1.1" bean-discovery-mode="all">

        <decorators>
            <class>com.curtisnewbie.SuperNumGenDecorator</class>
        </decorators>
    </beans>

**_Interceptors are invoked first, when there are decorators_**

---

## Events

**Events** allow beans to interact with no compile time dependency. One bean can define an event, another can fire the event and another can handle the event. These beans can be in seperate packages and even in separate tiers of the application. This design follows the **observer design pattern** in GoF.

For the **Event Producers**, they **are injected the Event object**, and **are responsible to fire events(of a specific type)**:

    public class BookService{

        @Inject
        private Event<Book> bookAddedEvent;

        public Book createBook(String title, float price, String, description){
            // create a new book
            Book book = new Book(title, price, description);

            // fire an Event for the type Book
            bookAddedEvent.fire(book);
            return book;
        }
    }

For the **Event Observers**, they **are awared of the fired Events of a specific type**, and they **have access to the fired objects**.

    public class InventoryService{

        public void addBook(@Observes Book book){
            //add the book to e.g., a database
            em.persist(book);
        }
    }

In this example, **Event Producers** injects _Event\<Book>_ for Book type, without explicit qualifier, the **Event Observers** of type _Book_ will be notified of a fired event. _BookService_ fires an Event of _Book_ when it creates a book, using **event.fire(bean) method**. The bean is fired to the **Observers**, the _InventoryService_ is notified the fired events and access the fired book through **@Observes**, and then it stores this book into database. **@Observes Book book** can be considered as a kind of subscription.

**_Events are not processed asynchronously_**

### Events with Qualifiers

We can also use Qualifiers, though it's optional.

    public class BookService{

        @Inject @BookAdded
        private Event<Book> bookAddedEvent;

        @Inject @BookDeleted
        private Event<Book> bookDeletedEvent;

        public Book createBook(String title, float price, String, description){
            // create a new book
            Book book = new Book(title, price, description);

            // fire an Event for the type Book
            bookAddedEvent.fire(book);
            return book;
        }

        public Book deleteBook(Book book){
            bookDeletedEvent.fire(book);
        }
    }

    public class InventoryService{

        public void addBook(@Observes @BookAdded Book book){
            //add the book to e.g., a database
        }

        public void addBook(@Observes @BookDeleted Book book){
            //delete the book from e.g., a database
        }
    }
