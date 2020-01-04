# Chapter 1

## Architecture

**_Java EE is a set of specifications (Umbrella Specification) implemented by different vendors and in different containers. It itself does not include the actual implementations. Containers are the JavaEE runtime environments that provide the services to componenets that they host, e.g., CDI._**

Java EE defines that its **runtime environment/ container** must support four types of componenets.

#### Components and Containers

1. Applets (that run in a **web browser**, web browser is the container)
2. Applications (normal Java SE/EE Programs)
3. Web applications (made of servlets, filters, listeners, JSP, JSFS and etc, runs on **Web Container**)
4. Enterprise applications (made of Enterprise Java Beans, Message Service, Transaction API, Async calls, timer services, RMI/IIOP and so on, and are executed in a **EJB container**)

#### Services

Containers provide the underlying services to the deployed componenets (e.g., webapps). Developers concentrate on implementing business login on the componenets, rather than addressing the technical problems of containers.

Java EE offers the following services:

- **Java Transaction API(JTA)**: Transaction democation API used by the application and the containers
- **Java Persistence API(JPA)**: ORM, JPQL and so on for Database Connection.
- **Validation** : Bean validation for contraints declaration and data validation.
- **Java Message Service(JMS)**: Asynchronously communications between components. P2P messaging and publish-subscribe model.
- **Java Naming and Directory Interface(JNDI)**: Objects binding and finding in a directory system.
- **JavaMail**: Email
- **JavaBeans Activation Framework**: A framework for hanlding data in different MIME types. Used by Java Mail.
- **XML Processing**
- **JSON Processing**
- **Java EE Connector Architecture**: Connectors allow connecting to Enterprise Information System from a JavaEE component.
- **Security Services**: Java Authentication and Authoriasation Services(JAAS) for access control, and more.
- **Web Services**: SOAP and RESTful web services. JAX-WS for XML webservices and the JAX-RS for RESTful services using HTTP protocol.
- **Dependency Injection**: Resources and dependency injection.
- **Management**: Containers and servers management.
- **Deployment**: Standardisation of application deployment.

Containers will provide full/partial implementation of these services to the deployment components.

#### Network Protocols

- HTTP
- HTTPS (HTTP + Secure Sockets Layers SSL)
- RMI-IIOP (Remote Method Invocation-Inter-ORB Protocol)

#### Declarative Programming

**Declarative programming paradigm** specifies how to achieve a goal, and this is achieved by **Annotations or/and Deployment Descriptors**.
