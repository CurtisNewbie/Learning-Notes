# Chapter 8 Callbacks, Timer Service, and Authorisation

Session beans (EJB) are container-managed components, they are backed by the container. Among the services provided by the container, thre of them are **life-cycle management**, **scheduling**, and **authorisation**.

**Lifecycle management** is similar to how the CDI beans are managed (Chapter 2). **_A lifecyle of a bean is a set of states, and at each state the associated callback funciton is invoked. Dependending on the type of the session bean (Stateful, stateless, singleton), their lifecyle differs._**

**EJB timer service** is the standard way to shcedule tasks.

**Authorisation** is managed within the business tier, where only certain groups of users can conduct specific operations regarding their roles.

## Session Bean Lifecycle

Client doesn't need to create instance of session beans, session beans are injected or obtained through JNDI lookup. The session beans are managed by the container throughout the lifecycle.

### Stateless and Singleton Session Bean

Stateless and singleton session beans do not maintain conversational state with a client. Stateless session beans serve one instance at the time, though the client being served is not specific. Singleton session bean allows concurrent access, while there is only one singleton bean.

**They share same lifecycle**:

1. Bean is initialised during **@Startup** or **@DependsOn** another singleton bean (if applied).
2. Client requests a reference to a bean (through **injection** or **JNDI lookup**).
3. Invoke **@PostConstruct** callback function if specified.
4. Wait for future calls or being removed (controlled by the container).
5. Invokes **@PreDestroy** callback function before destruction of the bean.

Their lifecycle is basically the same, however, the way that they are created and destroyed is different. **Singleton session bean** is created during startup (or first invocation) and destroyed when application closes. **Stateless session beans** are created and put into a pool, the container may maintain an appropriate number of them depending on the workflow. When the container decides to reduce the number of stateless session beans to free memory, some of them are destroyed.

### Stateful Session Bean

**Stateful session beans** are generated for a specific client. So the stateful session beans ramain in memory until the client "signout" or session timeout and so on. This raises the issue where if a client doesn't invoke its stateful session beans for a long enough time, the container has to somehow clear or preserve these beans to prevent running out of memory.

Container manages this by **Passivation** and **Activation**, passivation is the process of serialising the stateful session beans to a storage medium (e.g., hard disk), and activation is the process of deserialising the session beans back to the memory.

The states associated with these two processes are: **@PrePassivate** and **@PostActive**. These two callback functions allow certain operations to be conducted or resources to be handled before serialisation or deserialisation (e.g., close and reopen network connections).

**Lifecycle of Stateful Session Bean**:

1. Bean is initialised during **@Startup** or **@DependsOn** another singleton bean (if applied).
2. Client requests a reference to a bean (through **injection** or **JNDI lookup**).
3. Invoke **@PostConstruct** callback function if specified.
4. Wait for future calls or being removed (controlled by the container).
5. If the client remains idle, container invokes **@PrePassivate** method and passivates the bean.
6. If the client becomes active before timeout, the container activate the bean and invokes **@PostActive** callback functions.
7. Invokes **@PreDestroy** callback function before destruction of the bean.

## Callback Functions

There four callback annotations, two of them are common (as discussed in Chap2):

- **@PreConstruct**
- **@PreDestroy**
- **@PrePassivate**
- **@PostActive**

**Rules for Callback Functions**:

- Method must not have parameters and must return void.
- Method must not throw checked exceptions.
- Method must not be static or final.

Callbacks are typlically used to allocate or release resources.

    @Singleton
    public class CacheEJB{

        private Map<Long, Object> cache = new HashMap<>();

        @PostConstruct
        private void initCace(){
            cache.put(1L, "Cache Object one");
            cache.put(2L, "Cache Object Two");
        }

        //...
    }

Uses **@PostConstruct** to initialise things right after the instantiation of an instance.

    @Stateful
    public class ShoppingCartEJB{

        @Inject
        private DateSource datasource;
        private Connection dbConn;

        @PostConstruct
        @PostActivate
        private void init(){
            dbConn = datasource.getConnection();
        }

        @PrePassivate
        @PreDestroy
        private void close(){
            dbConn.close();
        }
    }

Uses **@PostActivate** to resume database connection after activation, and uses **@PrePassivate** and **@PreDestroy** to close database connection before passivation and destruction.

## EJB Timer Service

Timer Service are for scheduling tasks that the tasks are performed at certain time (e.g., sending a daily notification email or refresh cache after certain time). **_EJB Timer Serivce is a container service that allows the EJBs to be registerd for callback invocation._**

**_Timers are managed by container, and are by default persistent._** It means that the Timers are persisted even the container has been shutdown, tho it can be disabled. EJB notification may be scheduled based on the calendar-based schedule, or at a specific time, or after a duration, or at certain interval.

To use timer service, EJB needs to create a timer **automaticallly by container using @Schedule** or **gramatically using @Timeout**, then methods are registered (annotated) for callback invocation.

---

**_Stateless beans, singletons and Message-Driven Beans can be registered with timer service._**

**_Stateful beans can't and shouldn't use the timer!_**

---

### Calendar-Based Expression

additional src: https://docs.oracle.com/javaee/7/api/javax/ejb/ScheduleExpression.html

    javax.ejb.ScheduleExpression

Automatic timer creation by container uses **@Schedule** annotation and the **ScheduleExpression** class. **Attributes** that can be used for the calendar-based expression includes:

- second
- minute
- hour
- dayOfMonth
- month
- dayOfWeek
- year
- timezone

For example:

    year = "2008,2012,2016" dayOfWeek = "Sat,Sun" minute = "0-10,30,40"

There can be a list of or a range of years, days, and so on. There are **five forms** to specifiy the values for each attribute:

- Single value e.g., dayOfWeek = "0" (Sunday)
- Wild card e.g., dayOfWeek = "\*" (whole week)
- List e.g., dayOfWeek = "0,1,2" (sunday, monday, tuesday)
- Range e.g., dayOfWeek = "0-5" (from Sunday to Friday)
- Increments e.g., minute = "\*/15" (every 15 minutes)

### Declarative Timer Creation

Using the above Calendar-based expression, we can create timer in a declarative way using **@Schedule**.

    @stateless
    public class StatisticsEJB{

        // create two timers, one is everyday 9:30 am, another is Fri 5pm
        @Schedule({
            @Schedule(hour = "9", minute = "30"),
            @Schedule(hour = "17", dayOfWeek = "Fri")
        })
        public void generateReport(){
            //...
        }

        // everyday at 5:30 am
        @Schedule(hour = "5", minute = "30")
        public void updateStatistics(){
            //...
        }

        // every 10 minutes, non-persistent timer
        @Schedule(minute = "*/10", hour = "*", persistent = false)
        public void refreshCache(){
            //...
        }
    }

We can create two seperate timers as above. They are by default persistent. We can also make one non-persistent.

### Programmatic Timer Creation

addition src: https://docs.oracle.com/javaee/7/api/javax/ejb/TimerService.html

We can grammatically create timer by using **Dependency Injection** or **SessionContext**. We can get **TimerService** from the **SessionContext**, then uses the API of TimerService to create timer.

    @Resource
    SessionContext sessionContext;

    //...

    TimerService timerService = sessionContext.getTimerService();
    timerService.createCalendarTimer(scheduleExpression);

The **.createCalendarTimer() methods** has a parameter of **ScheduleExpression** object, which is a helper class that helps create the schedule with calendar-based expression.

    ScheduleExpression schedule = new ScheduleExpression().dayOfMonth("Mon").month("Jan");

We can also get the **TimerSerivce** through injection.

    @Resource
    TimerService timerService;

Once the **Timer** is created using **TimerService**, we then annotate a method with **@Timeout** which does something when the timer expires. For example, when we create a new customer, we want to create a timer for his/her birthday. Once the timer expires, we send the birthday email to the customer.

    @Stateless
    public class CustomerEJB{

        @Resource
        TimerSerivce timerService;

        public void createCustomer(Customer c){
            // create expresion
            ScheduleExpression birthSchedule = new ScheduleExpression().dayOfMonth(c.getBirday()).month(c.getBirthMonth());
            // create timer, callback the Customer object, and persist the timer
            timerSerivce.createCalendarTimer(birthSchedule, new TimerConfig(c, true));
        }

        @Timeout
        public void sendBirthdayEmail(Timer timer){
            // get the Serializable object passed to this callback function
            Customer c = (Customer) timer.getInfo();
            // send email
        }
    }

In the above example, **TimerSerivce** is injected into the EJB. **ScheduleExpression** is created using the customer birthday, and it's used as a parameter to create a **Timer**. **TimerConfig** is used to configure whether we want to pass the **Serializable object** to the callback function, and whether we want to persist the timer. Here, we pass the Customer object to the callback function, so that we can send email to the customer when the timer expires.

## Authorisation

EJB Security model controls access to the business tier based on the **role**, the business tier doesn't acutally handle **Authentication**, it only handles **Role-based Authorisation**. **_There are two way to authorise access, one is declarative authorisation that is controlled by EJB container, and one is programmatic authorisation that is controlled programmatically using JAAS_**.

### Declarative Authorisation

    javax.annotaiton.security

Declarative authorisation can be defined using **annotations** or **deployment descriptor**. It involes **declaring roles**, **assignning permission to methods or entire bean based on roles**, or **changing security identity temporarily**.

The Security Annotations include:

- **@PermitAll** (can be applied to bean or method, permit all roles)
- **@DenyAll** (can be applied to bean or method, deny all roles)
- **@RolesAllowed** (can be applied to bean or method, a list of roles that is allowed)
- **@DeclareRoles** (can be applied to bean only, define roles for checking)
- **@RunAs** (can be applied to bean only, temporarily assigns a new role)

For example:

    @Stateless
    @RoleAllowed({"user", "employee", "admin"})
    public class ItemEJB{

        @PermitAll
        public Book findBookById(long id){
            return em.find(Book.class, id);
        }

        @RolesAllowed("admin");
        public void deleteBook(Book book){
            em.remote(book);
        }

        @DenyAll
        public void deleteDatabase(){
            //...
        }

        //...
    }

This EJB is annotated with **@RoleAllowed**, where it allows roles: user, employee, admin. While for the method deleteBook(), it only allows role: admin. The method deleteDatabase() denies all access with **@DenyAll**, and the method findBookById() can be accessed by all roles with **@PermitAll**.

**_Roles are automatically created by container, the container scans the annotation @RoleAllowed when it initialises._**

**@DeclareRoles** is different in that it doesn't permit or deny any access. It is used to **declare roles for the entire applications**. In case of declaring security roles, either @DeclareRoles or @RolesAllowed is used or both. However, **_it makes more sense to declare roles in deployement descriptor, since it's for the whole application_**.

    @DeclareRoles({"HR", "Sales"})
    public class ItemEJB{...}

**@RunAS** is used to temporarily assign a new role to the principal. When using this annotation, the methods and EJBs (injected into it) will run using the assigned role.

    @RunAs("inventoryDept")
    public class PurchaseEJB{

        @EJB
        private InventoryEJB inventory;

        public Book createBook(Book book){
            inventory.addItem(book);
            //...
        }
    }

For example, when one invokes the EJB "PurchaseEJB", the principal automatically runs the methods, beans within "PurchaseEJB" with the role "inventoryDept".

### Programmatic Authorisation

    java.security.Principal

Declarative authroisation provides ease to use security protection. However, in case where a **finer grain of authorisation** is needed (only execute a block of code rather than a whole method), programmatic authorisation is needed.

We can use **SessionContext** to check the security role of the client and to access the **Principal** that identifies the caller.

    @Stateless
    public class ItemEJB{

        @Resource
        private SessionContext sessionContext;

        public void deleteBook(Book book){
            if(!sessionContext.isCallerInRole("admin"))
                throw new SecurityException("Only admins are allowed");
            else
                em.remove(book);
        }

        public void createBook(Book book){
            if(sessionContext.getCallerPrincipal().getName().equals("staff")){
                // book created by staff
                em.persist(book);
            }
        }
    }

In this example, **SessionContext** is injected, and is used to check security role and to get the Principle object for identification.
