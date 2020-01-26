# Chapter 9 Transactions

**_"A transaction is used to ensure that the data are kept in a consistent state. It represents a logical group of operations that must be performed as a single unit... and such an unit of work should be ACID"_**. Transaction management is a low-level concern, thus JavaEE provides abstract way to handle it either programmatically or declaratively.

## ACID

**_ACID stands for Atomicity, Consistency, Isolation and Durability._**

For example, when a money transferring happens, the account is updated and a log is created to log the transfer.

- **Atomicity**, these two operations must be performed as a single unit of work
- **Consistency**, data are either updated or rollbacked consistently
- **Isolation**, before the transaction is commited, the internal state should not be visible
- **Durability**, after the transaction is commited, the result should be visible and maintained

## Read Conditions

additiona src: http://javadata.blogspot.com/2011/05/dirty-readphantom-read-and-non.html

**_"Transaction Isolation cna be defined using different Read Conditions (dirty reads, repeatable reads, and phantom reads)... Read conditions describe what can happend when two or more transactions operate on same data at the same time."_**.

Read conditions:

- **Dirty Reads**: Dirty read occurs wherein one transaction is changing the record, and a second transaction can read this record before the original change has been committed or rolled back.
- **Repeatable Reads**: Non Repeatable Reads happen when in a same transaction same query yields different results. This happens when another transaction updates the data returned by other transaction.
- **Phantom Reads**: when a transaction same query executes twice, and the second result set includes rows that weren't visible in the first result set. This situation is caused by another transaction inserting new rows between the execution of the two queries.

I.e., **Dirty read** is essentially one reading the uncommitted changes from another. **Repeatable read** is essentially that same query executes twice wihtin one transaction and gets different results because others update the data. **Phantom read** is also that same query executes twice wihtin one transaction and gets different results but which is because others insert new data. The major difference between phantom reads and repeatable reads is whether other transaction inserts or updates the data.

## Transaction Isolation Levels

`Transaction Isolation Levels` are different levels that use different locking techniques to control the concurrency of transaction, each impact/solves the read condition above.

There are four types of isolation levels (from less restrictive to most restrictive):

- **Read Uncommitted**: all read conditions can occur.
- **Read committed**: transaction can only read committed data, dirty reads are prevented.
- **Repeatable Read**: transaction cannot change data that are being read by another transaction. Repeatable read and dirty read are prevented.
- **Serializable**: transaction has exclusive read, others cannot read and write the same data.

## JTA Local Transactions

**_`JTA Local Transactions` is when an application only has one single resource (e.g., one DMBS only)._**

The simplest example of an application is as follows:

    Application <--> Transaction Manager <--> Resource Manager (DB Driver) <--> Resource (DBMS)

_Application_ performs a number of operations, if needed some of them are handled by _Transaction Manager_ within transactions. _Transaction Manager_ is responsible to create the transaction and inform the _Resource Manager_ to commit or rollback a transaction, and the _Resource Manager_ commits the actual change to the resource.

**_It is not the application's responsibility to preserve ACID, application only decies to commit or rollback transaction, and the transaction manager prepares the resources to commit the changes._**

## Distribute Transactions and XA

EIS often uses more than one resource. Resources may be distributed across network, which requires special corrdination: `XA and Java Transaction Service(JTA/XA)`. Data changed by a single unit of work may need to be persisted in a database and sent to another resource within the same transaction.

To manage such complicated case, XA resource manager interface is needed, which stands for **eXtended Architecture**. XA is a standard specified by Open Group for **distributed transaction processing (DTP)**, which may involve multi-phases commit.

Aa a developer, just use JTA which is a higher level interface.

## Transaction Specifications Overview

**_"It's unlikely that you want to use the raw JTS/JTA APIs in Java. Instead you delegate transactions to the EJB container that contains a transaction manager (which internally uses JTS and JTA)._** JTA is the general API to start, commit rollback transactions in a resource. In a typical Java EE application, session beans establish the boundaries of a transaction, call entities to interact with the database, or send JMS messages in a transaction context.

    javax.transaction
    javax.transaction.xa

By default, transactions are managed by container which is called (`Container-Managed Transaction CMT`). With CMT, we only demacrate methods and class with the appropriate annotations. `Programmatic Transaction Demacration` is allowed for a fine-grained specification of when the transaction starts and ends. This is often the case where there are multiple methods involved in one single transaction.

## Container Managed Transaction

When no transactional annotation is applied, EJBs are by nature transactional, and the container automatically applies the default transaction management (**REQUIRED** attribute).

    @Stateless
    public class ItemEJB{

        @Inject
        EntityManager em;

        // REQUIRED attribute is applied for transaction management as default
        public Book createBook(Book book) {
            em.persist(book);
            return book;
        }
    }

In above example, even no extra annotation is provided, the container automatically intercepts the method call and checks if there is a **transaction context** associated with the call. If not (this is the case), the container begins a new transaction for the method call. When the method completes, container automatically commits or rollback the call.

### CMT Attributes

Excellent additional src (**must read**): https://www.ibm.com/developerworks/library/j-ts1/index.html

By default, **REQUIRED** attribute is applied. With annotations applied, the way the container manages the transaction can be changed.

CMT Attributes:

- **`REQUIRED (Default)`**: methods must always be invoked within a transaction. If there is no transaction context found it creates one, else it runs in client's transaction context. **_(Don't assume the client has a transaction context/within a transaction.)_**
- **`REQUIRES_NEW`**: container always creates new transaction for the method calls regardless. If client is in a transaction aleady, it temporarily suspends the current one, creates one for the current method call, and commit or rollback the current one. Once done, it resumes the previous transaction. Thus it has no affect on existing transaction when rollback happens. **_(Use it when we don't want rollback to affect the client, and only use it when we don't care about the final result even some of the transactions created are rollbacked/failed. One example will be to log a task with mutiple subtasks within a overall transaction, eventhough some of the subtasks in differnt new transactions are failed and rollbacked, we just don't care and keep going.)_**
- **`SUPPORTS`**: methods always inherit clients' transaction context if available. If not, it executes methods with no transaction. **_(This is case where the methods may not need transactions at all, for example, we read data from database as a single independent operation. In such case, we are ONLY reading data, and we don't need to maintain ACID. However, if we are both reading and updating data, we should use transactions.)_**
- **`MANDATORY`**: methods must always be invoked within a transaction, but it doesn't create one as in REQUIRED. If no transaction found, it throws `javax.ejb.EJBTransactionRequiredException`.
- **`NOT_SUPPORTED`**: methods cannot be invoked within a transaction. If transaction found, container suspends the transaction, invokes the methods and resumes the transaction.
- **`NEVER`**: disallows transaction at all. If transaction found, throws `javax.ejb.EJBException`.

**_`"The main point here is always to use either the MANDATORY or REQUIRED attribute instead of REQUIRES_NEW unless you have a reason to use it for reasons similar those to the audit example."` (from additional src)_**

---

To use the above CMT attributes, we must use the `@javax.ejb.TransactionAttribute` annotations or set the `<trans-attribute> element` in the `ejb-jar.xml`.

    @Stateless
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public class ItemEJB{

        @PersistenceContext(unitName = "dbsrc")
        private EntityManager em;

        // SUPPORTS attribute applied
        public List<Book> findBooks(){
            //...
        }

        @TransactionAttribute(TransactionAttributeType.REQUIRED)
        public Book createBook(Book book){
            //...
        }
    }

**@TransactionAttribute(TransactionAttributeType.SUPPORTS)** is applied at class level, thus **SUPPORTS** CMT attribute is applied on all methods. **@TransactionAttribute(TransactionAttributeType.REQUIRED)** is explicitly applied on createBook() method, which over-writes the strategy, thus it uses the **REQUIRED** CMT attribute.

### Marking CMT Rollback

In CMT, explicit rollback is not allowed. This is and should be managed by the container. Plus **_`Checked Exceptions do not trigger transaction rollback, only System Exceptions do (e.g., NullPointerException)`_**, there are many cases where we catch exceptions during transaction, we may want to mark the transaction to rollback AS A WHOLE. We do such by using `(SessionContext) context.setRollbackOnly()`.

    @Stateless
    public class ItemEJB{

        //...

        @Resource
        private SessionContext context;

        // rollback when Exception found
        public void sold(Item item){
            try{
                // complex, muti-steps operations
            } catch(Exception e){
                context.setRollbackOnly();
            }
        }

        // rollback when certain condition is violated
        public void updateStock(Stock newStock){

            //...

            if(newStock.size() < 0)
                context.setRollbackOnly();
        }
    }

In above example, we inject **SessionContext**, and uses its **.setRollbackOnly()** method to rollback when checked excpetions are found or certain conditions are violated.

### Exceptions and Transactions

**_Exceptions can be a way to rollback a transaction with proper annotation that changes the default behaviour._** As mentioned above, there are `Application Excpetion` (checked or Unchecked exceptions that extends Exception class) and `System Exceptions` (e.g., Runtime Exception). We can change the default behaviour, and rollback a transaction when specific **checked exceptions** is thrown.

We can **enable the rollback for an Application Exceptions**:

    // create an Exception class that has rollback enabled
    @ApplicationException(rollback = true)
    public class InventoryLevelTooLowException extends Exception{

        //...
    }

then **throws a Application Exception to cause the rollback**:

    @Stateless
    public class ItemEJB{

        //...

        public void sell(Item item) throws ItemNotExistException{
            //...

            // programmatically throws an Exception to cause rollback
            if(!hasItem(item))
                throw new ItemNotExistException();
        }
    }

## Bean Managed Transactions

`Bean-Managed Transaction (BMT)` is for providing fine-grained transaction management. For example, a CMT democrated method cannot generate more than one transaction within a method, and this is often the case to use BMT. **_To use BMT, we need to explicitly turn off CMT using `@javax.ejb.TransactionManagement`._**

    @Stateless
    @TransactionManagement(TransactionManagementType.BEAN)
    public class ItemEJB{...}

With BMT enabled, we will need to programmatically manage transaction via interface `javax.transaction.UserTransaction`. We can programmatically get a UserTransaction object by `(SessionContext) context.getUserTransaction()`.

    public void sell(Item item){

        // get a UserTransaction for transaction management
        var tran = sessionContext.getUserTransaction();
    }

Or inject it directly using **@Resource**:

    @Resource
    UserTransaction userTransaction;

    @Stateless
    @TransactionManagement(TransactionManagementType.BEAN)
    public class ItemEJB{

        @Resource
        SessionContext context;

        public void sell(Item item){

            // get a UserTransaction for transaction management
            var tran = context.get

            try{
                // starts transaction
                tran.begin();

                // do a bunch of things, one of them may fail
                item.decreaseAvailableStock();
                sendShippingMessage();

                if(!isAvailable(item))
                    // programmatically rollback
                    tran.rollback();
                else
                    // programmatically commit
                    tran.commit();
            } catch (Excpetion e){
                // programmatically rollback
                tran.rollback();
            }
        }
    }

With **UserTransaction** object, we programmatically **being()**, **commmit()** or **rollback()** the transaction.
