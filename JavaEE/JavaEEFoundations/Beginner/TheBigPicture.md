# The Big Picture of JavaEE

<h1>Overview</h1>

<h3>Four Major Editions In Java</h3>

    Java SE - Standard Edition
    Java ME - Micro Edition
    Java FX - Effects
    Java EE - Enterprise

<h3>Enterprise Applications</h3>

    - Multi-tiered
    - Scalable
    - Reliable
    - Secure

<h3>Tiered Applications</h3>

    Client Tier  <->  Middle Tier  <->  EIS Tier

Client Tier:

    1. Java applications
    2. Web Browsers

Middle Tier:

    1. Web Tier - dynamically generate webpages for interactions with clients
    2. Business Tier - handler client requests, and processes applications data
    3. Interoperability Tier - handle interactions with external services through such as messaging services

EIS (Enterprise Information Systems) Tier:

    1. DBMSs
    2. Mainframes

<h3>Java SE vs. Java EE</h3>

Java SE:

    APIs for core operations, such handling collections
    JVM is the container
    Lower-level services

Java EE:

    APIs for transactions, messaging, persistence etc.
    Code runs in a container
    High-level services
    Provides standardised programming model and APIs that reduces complexity

<h3>Java EE Programming Model</h3>

JavaEE programming model focuses on:

    Convention over configuration
    Container takes default decisions to bring services
    Metadata are used to deviate from the default decisions (or convention)

For Example, to manage persisted data in JavaSE, the low-level APIs of JDBC are used
to construct the query. However, the code can be hard to maintain or verbose, which
largely reduces the productivity. With JavaEE, the ORM technology such as Hibernate solves
this problem, without the need of manual mapping.

Annotations are used as "Convention" that the virtual machine used to know default decisions in runtime.

<h1>Java EE Architecture</h1>

A Few Key Concepts In Java EE Architecture:

    - Container
    - Services
    - Components
    - Packaging
    - Deployment
    - Protocols

Essentially, the Java EE Architecture is centered around a runtime environment (which is called a
<strong>container</strong>), wherein the container provides <strong>services</strong> through the <storng>components</storng> that it hosts, such as lifecycle management, dependency injections. Components use standardised APIs to communicate with other components. A component must be
<strong>packaged</strong> before being <strong>deployed</strong>. After they are being deployed,
there are several <strong>protocols</strong> that can be used to communicate with these components.

<h3>Container</h3>

    - A runtime environment
    - It hides technical complexity
    - It enchances portability
    - It hosts applications

<h3>Component</h3>

    - Static or Dynamic Web Pages
    - Service-side code that handler busiess data and logic

<h3>Services</h3>

Container provides services to its componenets

    - Security
    - Transaction Management
    - Connectivity
    - etc.

Further, services are configurable and the configuration is isolated from the actual code.

<h3>Metadata</h3>

    - Annotation
    - XML deployment descriptor

<h3>The Structure of An Example JavaEE Application</h3>
 
A simplified structure of an JavaEE application will include the following, and these 
will be assembled/packaged together as an archived file that needs to be deployed to a container.

A container can handle several applications, i.e., several applications can be deployed to the same
container, wherein these applications are isolated. Each application in the container will have its
own resources, components and class-loader.

    - A number of web pages and web resources
    - Components (such as one for DB connection, business components)
    - Deployment descriptos
    - etc...

<h3>Protocols</h3>

    - HTTP
    - HTTPS
    - RMI (Remote Method Invocation)
    - IIOP (Internet Inter-ORB Protocol)

<h1>Java EE Services</h1>

Business Tier - business logic

    - Persistence and RDBMS
    - Transaction Management
    - Messaging Services
    - Validation
    - Batch Processing

Web Tier

    - Servlets
    - Web Pages
    - Web Sockets

Interoperability Tier

    - SOAP Services
    - REST Services
    - JSON
    - Messaging Services
    - Email

Common Layer/Tier

    - Injection Service
    - Interception
    - Security
    - Concurrency
