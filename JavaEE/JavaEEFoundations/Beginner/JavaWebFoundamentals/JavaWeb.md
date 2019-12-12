# Java Web Fundamentals

Main Contents:

    - Servlets
    - Java ServerPages
    - Filters for compressing data
    - Event handlers

<h2>1.Java Web Programming</h2>

Java Web application is typically built on top of:

    - Servlets
    - Java Server Pages
    - Filters
    - Listeners

And frameworks such as Struts is also built on top of these specifications.

<h3>Tomcat Server</h3>

Note that, to start up or shutdown the <b>Tomcat Server</b>:

    $CATALINA_HOME/bin/startup.sh
    $CATALINA_HOME/bin/shutdown.sh

About how to install Tomcat, see following sources:

    https://oracle-base.com/articles/linux/apache-tomcat-9-installation-on-linux
    https://help.ubuntu.com/lts/serverguide/tomcat.html
    https://www.digitalocean.com/community/tutorials/install-tomcat-9-ubuntu-1804
    https://linuxize.com/post/how-to-install-tomcat-9-on-ubuntu-18-04/
    https://tecadmin.net/install-tomcat-9-on-ubuntu/

In the directory of the Tomcat server, there is a folder named <b><i>"webapps"</i></b> which contains (or hosts) the web applications.

<h3>What Is A Web Application</h3>

A web application is <b>a unit of deployment</b> that:

<ul>

    <li>contains content and configuration</li>
    <li>listens to the request of or is responsible for a <b>Base URL</b></li>
        such as: https://<b>someserver</b>/<b>webapp</b>/somepages
    <li>may contain static content(e.g., HTML, resources)</li>
    <li>may contain dynamically generated content (e.g., servlets, server pages) </li>

</ul>

<h3>Structure of A Webapp</h3>

A webapp is usually packaged as a .war file or Web Archive file for deployment. Wherein there are a number of folders, such as the folder for resources (e.g., images), the WEB-INF folder, the META-INF folder and so on.

<b>META-INF</b> folder contains

<ul>
    <li>the "context.xml" file that specifies the context of the webapp, such as the 
    JDBC connection configuration. However, it may or may not be used.</li>
</ul>
Most importantly, <b>WEB-INF</b> folder contains:

<ul>
   <li>"/web.xml" The xml file for webapp configuration and description</li>
   <li>"/jsp/" Java Server Pages</li>
   <li>"/classes/" Java compiled classes</li>
   <li>"/lib/" JAR files or dependency</li>
</ul>

<h3>Manager Webapp on Tomcat</h3>

In Tomcat, there is a default webapp called "manager" that allows you to manage the server using a GUI interface. However, to use it, configuration such as roles and user credentials need to be setup in <b>"\$CATALINA_HOME/ conf/tomcat-users.xml"</b>.

    <role rolename="manager-gui"/>
    <user username="someuser" password="somepassword" roles="manager-gui"/>

More specifically, these roles are specified in <b>"/\$CATALINA_HOME/webapps/
manager/WEB-INF/web.xml"</b>

About manager webapp, see:

    <https://tomcat.apache.org/tomcat-9.0-doc/manager-howto.html>

Manager webapp is useful in that it allows the admin to undertake certain managing operations on the server, such as upload, reload, or stop the deployed webapps. Further <b>Maven</b> can be used in conjunction with Tomcat (or more specifically Manager webapp) to facilitate the webapp deployment.

To use Maven with Tomcat, this plugin needs to be installed:

    <!-- https://mvnrepository.com/artifact/org.apache.tomcat.maven/tomcat7-maven-plugin -->
    <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <url>http://localhost:8080/manager/text</url>
            <server>IdOfServer</server>
            <path>/myappurl</path>
        </configuration>
    </plugin>

The <i>url</i> tag is a uniform one that doesn't need to be changed unless the remote deployment is needed. <i>server</i> tag refers to the id of the server, which is speicified in maven's setting in <b>"\$MAVEN_HOME/conf/settings.xml" </b>, see below. The <i>path</i> tag is used to specify the URL Base of the webapp, i.e., where you want to deploy it in the webapps directory in Tomcat.

    <servers>
        <id>serverID</id>
        <username>managername</username>
        <password>managerpassword</password>
    </servers>

To deploy/undeploy the war package through maven:

    mvn tomcat7:deploy
    mvn tomcat7:undeploy

About maven deployment setup, see:

    http://tomcat.apache.org/maven-plugin-2.2/
    https://www.baeldung.com/tomcat-deploy-war
    https://mvnrepository.com/artifact/org.apache.tomcat.maven/tomcat7-maven-plugin/2.2

<h2>2.Servlets</h2>

<h3>Servlet Interface</h3>

Every servlet implements the <b>Servlet Interface</b>, which contains methods describing <b>Servlet Lifecycle</b>.

    public void init(ServletConfig);
    public void service(ServletRequest req, ServletResponse resp);
    public void destroy();
    public ServletConfig getServletConfig();
    public String getServletInfo();

As shown above, there are three main stages in the servlet lifecyle, 1) initialisation of servlet, 2) provision of services and 3) destruction of servlet.

<b>ServletRequest</b> object

contains details of the incomming request and parameters, can be used by any type of service such as http, ftp and so on.

<b>ServletResponse</b> object

generate outgoing response to the clients, can be used by any type of service such as http, ftp and so on.

<b>ServletConfig</b> object

get servlet configuration information such as database connection string, name of servlet and so on.

<h3>Abstract Class GenericServlet</h3>

When we implement a servlet, we tend not to implement the Servlet interface directly, there is a helper class <b>GenericServlet</b> that helps implementation. However, this class is not protocol-agnostic, i.e., it doesn't provide protocol specific implementation.

<h3>HttpServlet</h3>

    GenericServlet <- HttpServlet

HttpServlet class is a protocol-specific class for HTTP protocol, it's a subclass of GenericServlet. See examples below.

    public void service(HttpServletRequest req, HttpServletResponse resp){...}
    public void doGet(HttpServletRequest req, HttpServletResponse resp){...}
    public void doPost(HttpServletRequest req, HttpServletResponse resp){...}
    public void doPut(HttpServletRequest req, HttpServletResponse resp){...}

These methods are corresponding to the request and methods specified in HTTP, such as Get and Post. In service() method, we use getMethod() to check what the request is, and then we respond to such request by calling the correct methods, such as doGet(), doPost() or doPut().

    public void service(HttpServletRequest req, HttpServletResponse resp){

        String method = req.getMethod();
        if(method.equals("GET"))
            doGet(req, resp);
        else if(method.equals("POST))
            doPost(req, resp);
        else
            ...
    }

<h3>To Implement a Servlet</h3>

To implement a servlet for http protocol, we extends from the HttpServlet and overides thoses methods (that are for the servlet lifecyle) and its helper methods (e.g., doGet() and doPost()). However, without <b>Servlet Mapping</b>, we cannot access to this servlet. We need to add an annotation <b><i>"@WebServlet("/home")"</i></b>, so that we can access this servlet through the url "https://server/home".

    @WebServlet("/home")
    public class FirstServlet extends HttpServlet{
        ....
    }

or

    // mapped to multiple url patterns
    @WebServlet(urlPatterns = {"/home", "/family"})
    public class FirstServlet extends HttpServlet{
        ....
    }

<h3>Working with Maven</h3>

Use Archetype:

    -DarchetypeArtifactId=maven-archetype-webapp

Javax dependency is needed:

    <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>

If this archetype installed, the following will be automatically specified:

    <packaging>war</packaging>

To package this project/webapp to .war file, we execute command:

    mvn package

or

    war:war

<h3>Example - FirstServlet</h3>

In the example, FirstServlet project, the packaged .war file will be named exactly as the artifactId specified in pom.xml file. (e.g., "firstservlet.war"). We then deploy this .war file using the "manager" webapp in tomcat. The URL base for this servlet will be as follows:

    "https://localhost:8080/firstservlet/home"

<b>"/firstservlet"</b> is the name of the webapp.<br>
<b>"/home"</b> is the base url of this servlet, which is specified using
<i><b>"@WebServlet("/home")"</b></i> annotaion.

<h2>Request Routing</h2>

Server routes request to servlet using configuration information, which is held in <b>web.xml</b>. One servlet instance handles all requests to the associated url-pattern, not just one single base url, see below.

    "GET /someapp/someservlet HTTP/1.1" ->> web.xml(mapped by container) ->> associated Servlet

In <b>WEB-INF/web.xml</b>:

    <web-app>
        <!-- specify a servlet and its associated class, or i.e., give the servlet a name -->
        <servlet>
            <servlet-name>DemoServlet</servlet-name>
            <servlet-class>com.curtisnewbie.MappingDemo</servlet-class>
        </servlet>

        <!-- specify the url pattern that mapped to this servlet -->
        <servlet-mapping>
            <servlet-name>DemoServlet</servlet-name>
            <url-pattern>*.demo</url-pattern>
        </servlet-mapping>

        <servlet-mapping>
            <servlet-name>DemoServlet</servlet-name>
            <url-pattern>/demo</url-pattern>
        </servlet-mapping>
    </web-app>

As shown in previous "firstservlet" example, the base url can be mapped to a servlet by the annotation "@WebServlet", it can also be mapped based on the configuration in web.xml. As in above example, the class <i>MappingDemo</i> is given a name called <i>DemoServlet</i>, and this servlet is mapped to url patterns

    "*.demo" and "/demo"

I.e., any url request like:

    "https://localhost:8080/servletmapping/demo"

or

    "https://localhost:8080/servletmapping/asdfasdf.demo"

will be mapped to this servlet, executed by the class "MappingDemo". It is worth noting that tag, <b><i>servlet-name</i></b> is used in web.xml as an identifier.

<h2>HTTP Request and Response Processing</h2>

<h3>HTTP Request</h3>

HTTP Requests such as <b>GET</b>, <b>POST</b> are processed by the helper methods in
class HttpServlet. The class <b>HttpServletRequest</b> is a wrapper of an HTTP request.

    For example, in doGet(HttpServletRequest req, HttpServletResponse resp){

        // We can get HTTP Headers, here we are extracting HOST from headers
        String strHost = req.getHeader("HOST");

        // Read Content type of the request body
        String contentType = req.getContentType();

        // Read parameters
        String param_id = req.getParameter("uid");

        // Read request body
        BufferedReader reader = req.getReader();
        ...
    }

Note that <b>HTTP Headers</b> consist of three parts, the <b>Request Headers</b>, <b>General Headers</b>, and <b>Entity Headers</b>. They are shown as follows. The <i>"POST / HTTP/1.1"</i> refers to a POST action.

After this line, the first part is the Request Headers that specify the user-agent, accept-type, accept-language and so on. The second part is the for the general purpose that apply to the message as a whole. The third part is the Entity Header, that apply to the body of the request, such as the Content-Length.

    POST / HTTP/1.1

    HOST: localhost:8000
    User-Agent:Mozilla/5.0 ...
    Accept: text/html,application/....
    Accept-Language: en-US...
    Accept-Encoding: gzip, ...

    Connection: keep-alive
    Upgrade-Insecure-Requests:1

    Content-Type: multipart/form-data...
    Content-Length: 345

More on HTTP Messages: https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages

Further, in the above example of HttpServletRequest, parameters in a HTTP request can be extracted. <b>Parameters</b> are variables given and specified in the Http URL after the ? question mark, or if the request has a body, parameters can be from the body (such as from a form).

    "http://someserver/someapp/someservlet?uid=bob"

For example, here, we can extract the paramter "uid" from the request as follows:

    // id will be "bob"
    String id = reqest.getParameter("uid");

<h3>HTTP Response</h3>

<b>HttpServletResponse</b> is a warpper of the potential HTTP response.

    For example, in doGet(HttpServletRequest req, HttpServletResponse resp){

        try{
            // say if not logged on, we can send redirect response, ask user
            // to "/logon" servlet
            if(notLoggedOn()){
                resp.sendRedirect("/logon");
                return;
            }

            // we can set content type and header of the response
            resp.setContentType("text/xml");
            resp.setHeader("X-Custom-Header", new Date());

            // write message to response
            PrintWriter out = resp.getWriter();
            out.write("<message>some message</message>");

        }catch(Exception e){
            // if we catch exception, we can send error via response back to the client
            resp.sendError(response.SC_INTERNAL_SERVER_ERROR);
        }
    }

<h3>Demo HttpRequestAndResponse</h3>

In <b>doGet()</b> method, we do a simple request and response handling, that it gets "name" parameter from the request, and we print out a message and respond to the client. The URL can be as follow.

    "http://localhost:8080/reqrep/react?name=curtis"

Wherein, <i>"reqrep"</i> is the name of the webapp, <i>"/react"</i> is the url-pattern mapped to a servlet, and <i>"name=curtis"</i> is a parameter in this request. If not parameter provided or this URL doesn't formatted correctly (e.g., without ?), the method <b>getParameter("name")</b> will simply return null just like no parameter provided.

In <b>doPost()</b> method, we do the similar operation as in doGet() method, except that doPost() method is meant to push data to the server. So, when we receive a <b>POST</b> request, we may need to do some processing as above using the given parametesr. In this demo, doPost() method extract parameters from the <i>form</i> tag, and redirect to another servlet.

The parameters are taken as follows:

     <form action="react" method="POST">
        <p>
            Name: <input type="text" name="name" />
        </p>
        <p>
            <input type="submit" value="Enter Your Name" />
        </p>
    </form>

    In servlet:

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        // get parameter name (case-sensitive) from the form
        String name = req.getParameter("name");

        if (name != null) {

            // redirect it to the login.jsp
            resp.sendRedirect("login.jsp");
        }
    }

<h3>Change Content Type</h3>

We can change the content-type of the response to such as xml as follows:

    resp.setContentType("text/xml");

<h2>3.JavaServer Pages</h2>

A <b>.jsp</b> file or <b>Java Server Page</b> file is compiled into a servlet when it is deployed. The JSP file looks quite similar to a HTML, and when it's compiled, the HTML like static content is written out to the client.

The compiled files are stored in:

    $CATALINA_HOME/work/Catalina/localhost/...

<h3>Directive - Include Directive</h3>

In a simple JSP file, most of the tags are HTML elements, such as header, secion, body and so on. If we want to reuse some of the element in different webpages, we can use <b>directives</b>. For example, in a simple jsp file we have:

    <header class="navigation header">
        <nav>
            .......
        </nav>
    </header>

And then we want to reuse this header, we can create a new jsp file, say <i>"\_beautiHeader.jsp"</i>, and cut and paste all the above HTML code in to this <i>"\_beautiHeader.jsp"</i> file. When we want to reuse it, we use <b>Include Directive</b> as bellow:

    <%@include file="_beautiHeader.jsp"%>

This element, or this part of the content will then be embeded into the currnet jsp file during the <b>translation time</b> or the <b>compilation time</b> of this page. There will also be resources that are dynamically included, but these resources are included during the lifecyle of a servlet rather than being statically compiled.

<h3>Directive - Page Directive</h3>

As JSP is compiled by server (e.g., Tomcat) acting like a servlet, we can use <b>Page Directive</b> modify the behaviours of this JSP during the servlet lifecycle. Such as follow:

    <!-- change base class  -->
    <%@page extend="com.curtisnewbie.pkg.SomeClass"%>

    <!-- change buffer size of the generated text/html output-->
    <%@page buffer="8kb"%>

    <!-- change autoFlush behaviour, it's true by def. When it's false, it throws
    exception when buffer is full -->
    <%@page autoFlush="true"%>

    <!-- set content type, it's text/html by def. It can be such as xml, json, etc. -->
    <%@page contentType="" %>

    <!-- set errorPage, when exception thrown, we can specify an error page manually. -->
    <%@page errorPage="somePage.xml" %>
    <%@page errorPage="somePage.html" %>
    <%@page errorPage="somePage.jsp" %>

    <!-- Once we want to use a JSP as an error page rather than a xml page, we need to set this -->
    <%@page isErrorPage="true" %>

<h3>Example of Setting Error Page - ErrorHandling</h3>

As mentioned above, we can use <b>page directive</b>to specify how error page is assigned and create JSP as error page. However, this example aims to show how we can manually manage error, such way to handle exceptions is very unusual in normal situations.

Error pages are shown when exceptions are thrown, we may choose to handle them or display a error page, such as <i>404 for not found web pages</i>.

First of all, we create a JSP (error.jsp), and we set it to be an <b>Error Page</b> by using page directive (<b><%@page isErrorPage="true"%></b>), then we can use various methods such as <%=exception.getMessage()%> to gain information of the exception passed to this JSP.

    <%@page isErrorPage="true"%>

    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Error Page</title>
    </head>
    <body>

        <h2>Error</h2>

        <!-- we use methods to get exception information-->
        Exception Message:<%=exception.getMessage()%><br>
        Whole Exception:<%=exception.toString()%>

    </body>
    </html>

Then, in <b>WEB-INF/web.xml</b> file, we need to specify the error-page that we want to show for the specified error-code. Here, we direct the 500 error-code to the error.jsp.

    <error-page>
        <error-code>500</error-code>
        <location>/error.jsp</location>
    </error-page>

<h2>4. MVC Architecture</h2>

    Model - the data we'd like to display (Java Bean, POJO)
    View - where the data are displayed, e.g., JSP, HTML, XML, JSON
    Controller - bringing model and view together (servlet and listener)

The interactions or flow in MVC style application:

    1. Client -> send request to -> servlet
    2. Servlet (acts as controller) -> create a Java Bean (Model and data)
    3. Servlet -> pass the bean to the JSP (so that data are displayed), the bean may be stored for current session, so that only the current user can access to it.

<h2>Explanation of Demo - "SimpleMVCWebApp"</h3>

First of all, the <i>"com.curtisnewbie.app.Account"</i> is a <b>Java Bean</b> or <b>POJO</b>
It consists of basic Getter methods and Setter methods.

Second, this webapp contains two Jsp files, one is the <i>index.jsp</i> that is located directly under webapp/ folder, which is exposed to the public. This also means that we can access it as follows:

    http://localhost:8080/simplemvcwebapp/

    or

    http://localhost:8080/simplemvcwebapp/index.jsp

Another JSP file is the <i>account.jsp</i>, that is located under WEB-INF/jsp/. This is hidden from the public, and it can only be access when the server dispatch it to the clients.

In <i>index.jsp</i>, we use POST method to pass the parameters to the server as follows:

    <form action="login" method="POST">
        <p>Name: <input type="text" name="name" id="nameInput"></p>
        <p>Password: <input type="password" name="password" id="passwordInput"></p>
        <p><input type="submit" value="Login"></p>
    </form>

This POST method is handled by the Login.java, the "login" url is mapped to Login.java as follows:

    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>com.curtisnewbie.app.Login</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>

This POST request is handled as follows:

    if (name == null || name.isEmpty() || pw == null || pw.isEmpty()) {
        // for illegal login credentials, redirect it to index.jsp
        resp.sendRedirect("index.jsp");
        return;
    }

We use sendRedirect() method to redirect the client to the index.jsp, if they didn't provide name and password. Then, if the credentials are given, we create a Java Bean and load some data in it. (This bean is for demo only, so the password is in plaintext).

    switch (name) {
        case SampleData.name1:
            if (pw.equals(SampleData.pw1)) {
                // create bean
                account = new Account();
                account.setName(SampleData.name1);
                account.setDeposit(SampleData.deposit1);
            }
            break;

        case SampleData.name2:
            if (pw.equals(SampleData.pw2)) {
                // create bean
                account = new Account();
                account.setName(SampleData.name2);
                account.setDeposit(SampleData.deposit2);
            }
            break;
        }
    }

If the credentials are correct, we create a RequestDispatcher to commit further processing operations (using <b>include</b> method, the request and response objects are passed to other jsp for further processing, then they are returned back to this JSP, that's why it's called further processing) or to forward back to the client or other JSP (using <b>forward</b> method, no more processing is needed in this servlet).

    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/account.jsp");

    // load bean to request scope
    req.setAttribute("account", account);

    // forward request and response
    dispatcher.forward(req, resp);

Notice that we load the bean to servlet using httpServletRequest.setAttribute() method, this allows the JSP to access to this bean and dynamically generate data. This is within the <b>request scope</b>, that this is only accessible in this request. There are also session scope and global scope.

If we want to make it in the <b>global scope</b>, it will be accessed globally. We can also pass the bean like this.

    getServletContext().setAttribute("globalBean", bean);

If we want to make it in the <b>session scope</b>, which means it will be accessiable within the session, including other servlets in this session, it will be like this:

    req.getSession().setAttribute("sessionBean", bean);

Further, when the scope (e.g., session or request) is finished, the beans within the scope are then destroyed.

When we forward this request and response to the account.jsp, we expect that the account.jsp will extract the information from the bean. In account.jsp, we first <b>import the Account.java using the page directive</b>, so that we can extract data from the bean:

    <%@page import="com.curtisnewbie.app.Account" %>

Then we <b>get the bean from the request</b> as follows:

    <%
        Account account = (Account)request.getAttribute("account");
    %>

Within <b><%</b> and <b>%></b>, java code can be executed as usual. Then we can use the directive to gain the output (which is basically the same as "out.write(account.getName()")

     <title>Mate: <%=account.getName() %></title>

     ...

     <section>
        <h1>Account Info</h1>
        <p>You name is: <%=account.getName() %></p>
        <p>Your acount has: <%=account.getDeposit() %></p>
    </section>

This is how simple MVC works, the Account bean acts as the <b>Model</b>, the Login.java servlet acts as <b>Controller</b>, and the index.jsp and account.jsp act as the <b>View</b>.

<h2>Summary of JSP</h2>

JSPs are a mixture of

<ul>
    <li>Text</li>
    <li>Script</li>
    <li>Directives</li>
</ul>

<h2>5. Expression Language</h2>

As shown in above notes, we can execute java code in JSP file wihtin <b><%</b> and <b>%></b>, and we can also output some data using simple directive such as <b><%= %></b>. However, this relies on Java as the scripting language, and it becomes difficult to develop the dynamic pages. JSPs are the views that should not be used to execute business logics. Such that, JSP2.0 introduced the Expression Language, which is easy to use that doesn't need too much knowledge of Java.

<h3>Using the Expression Language</h3>

The syntax of EL is as follows:

    ${  expression   }

And we can used it anywhere in the page to output the value of the expression, such as

    <title>Name: ${user.name}</title>

With the EL, the Java Bean is also heavily used, here in this example, user is the name of the bean, and the name is the attribute of the user object. With EL, we don't need to get the user bean from the request object, and we don't call the getter method to get the attribute values.

    <!-- No Need for this!!! -->
    <%
        User user = (User)request.getAttribute("user");
    %>

    <!-- No Need for Getter and this directive!!! -->
    <title>Name: <%= user.getName() %></title>

The bean that is placed in the scope in servlet will be looked for by the JSP automatically. The JSP file finds the bean with the name of the bean (e.g., user) in the request scope, session scope and global scope.

<h3>Using the Java Beans</h3>

To use the Java Beans with EL, the syntax is slightly changed. We don't need to use the getter methods to get the attributes of the Java Beans, the syntax is as follows:

    // dot syntax
    ${ someBean.attributeName }

    or

    // [ ] syntax
    ${ someBean["attributeName"]}

Both syntaxs can be used. It should be noticed that the Java Beans can be nested, which means that the attribute of a Java Bean can itself be a Bean as well, so we can do something like this to extract data from a nested structure:

    ${ someBean.anotherBean.moreBean.stringValue }

    or

    ${ someBean["anotherBean"]["moreBean"]["stringValue"] }

EL provides access to a set of implicit objects:

    Except pageContext, all of them are Maps:

    pageContext         pageScope
    requestScope        sessionScope
    applicationScope    param
    paramValues         header
    headerValues        cookie
    initParam

And we can access to these implicit objects in the JSP files directly:

    Host header is ${ header.host }

We can also use EL to access the elements stored in <b>data structures</b>, such as <b>List</b>, <b>Maps</b>, <b>Array</b> etc. For example:

We first create a bean class called <i>Titles</i>, which contains an Array as its attribute.

    public class Titles {
        private String[] names;

        public Titles(){

        }

        public void setNames(String[] n){
            this.names = n;
        }

        public String[] getNames(){
            return this.names;
        }
    }

We then create a bean of this class and load it to the <b>request scope</b>:

    Titles titles = new Titles();
    titles.setNames(new String[]{"apple", "banana", "cat"});
    req.setAttribute("titles", titles);

Then in JSP, we can access to this bean and its attribute using EL:

    First Title: ${ titles.names[0] }
    Second Title: ${ titles.names[1] }
    Third Title: ${ titles.names[2] }

For Map data structure, we use the following syntax, but it quite similar:

    ${ mapObj[keyvalue] }

    <!-- if the keyvalue is number -->
    ${ mapObj[1234123] }

    <!-- if the keyvalue is string -->
    ${ mapObj["apple"] }

<b>The practical usage of EL and Java Beans will be creating various nested JavaBeans for such as settings (e.g., class name for css to change styles), variables (e.g., names in the navigation panel), data (e.g., name of user and so on) so that the webpages can be reused and very dynamic.</b>

<h3>EL and Operators</h3>

Even though, EL is used for basic operations, it can use operators, such as:

    Arithmetic: +, - (binary), \*, / and div, % and mod, - (unary).

    String concatenation: +=.

    Logical: and, &&, or, ||, not, !.

    Relational: ==, eq, !=, ne, <, lt, >, gt, <=, ge, >=, le. Comparisons can be made against other values or against Boolean, string, integer, or floating-point literals.

    Empty: The empty operator is a prefix operation that can be used to determine whether a value is null or empty.

    Conditional: A ? B : C. Evaluate B or C, depending on the result of the evaluation of A.

    Lambda expression: ->, the arrow token.

    Assignment: =.

These are typically used in conjunction with Tags as follows:

    //we first set the name of a bean, when we create this bean
    Person person = new Person();
    person.setName("curtis");
    req.setAttribute("person", person);
    ...

    <!-- we then use EL and operators to verify if the neame is "curtis". This expression will
    simply return true. -->
    <h2> ${ person.name == "curtis" }</h2>

<h3>Summary of EL</h3>

Expression Language:

<ul>
    <li>is not a full language</li>
    <li>can access to propertices </li>
    <li>use '.' or '[ ]' syntax</li>
    <li>can access to built-in implicit/intrinsics objects</li>
    <li>can use a set of operators</li>
    <li>can access Collection objects</li>
</ul>

<h2>Explaination of Demo - "ELDemo"</h2>

Two JSP files, one is <i>index.jsp</i> for getting information from the client using <b>POST</b> method. Another is <i>login.jsp</i> for displaying the information that we have previously extracted using <b>Expression Language</b>.

In index.jsp, we get POST request with simple form:

    <form action="login" method="POST">
        <p>Name: <input type=" text" name="name"></p>
        <p>
            Three Favourite Veggie: <br>
            <input type="text" name="veg1"><br>
            <input type="text" name="veg2"><br>
            <input type="text" name="veg3"><br>
        </p>
        <p>
            <input type="submit" id="submit" value="Submit">
        </p>
    </form>

We then process this request in Login.java servlet:

    // construct a bean
    User user = new User();
    user.setName(name);
    user.setVeggie(veggie);

    // dispatch bean to login.jsp
    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
    req.setAttribute("user", user);
    dispatcher.forward(req, resp);

This bean is placed into <b>Request Scope</b>, we can access it in login.jsp using EL:

    <p>You are: ${ user.name }</p>
    <p>Your three favourite veggie:</p>
    <ul>
        <li>${ user.veggie[0] }</li>
        <li>${ user.veggie[1] }</li>
        <li>${ user.veggie[2] }</li>
    </ul>

    1 + 1 = ${ 1 + 1}

    <p>Your Host: ${ header.host }</p>

<h2>Standard Tag Library</h2>

Java has a <b>Standard Tag Library</b> called <b>JSTL</b> that consists of a set of tags. Even though, we are able to run Java code in JSP, we still need Tags to:

    Provide encapsulation of UI Logic

These tags can be used to replace script on a page, that helps encapsulates these unnecessary script and code. In JSTL, these tags are standardised, and can be used by non-Java programmers for developing JSPs.

<b>Each individual tag is a Java Class.</b> The <b>Tag Metadata</b> of the Tags are held in <b>Tag Library Descriptor(TLD) File</b>, the TLD file may look similar to this:

    <taglib>
        <tlib-version> </tlib-version>
        <jsp-version> </jsp-version>
        <short-name> </short-name>
        <url> </url>

        <tag>
            <name> </name>
            <tag-class> </tag-class>
        </tag>
    </taglib>

First of all, we need to <b>import the appropriate JSTL tag library in the JSPs</b> in order to use them, it will be as follows:

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

To use this library, we also need to setup maven for this dependency:

    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

The prefix c and the uri refer that we are using core tag library, with the core tag lib we can do conditional operation or iterate elements (e.g., from a list)

    <%-- Conditional tag --%>
    <c:if test="\${empty account.name}">
        Error:Name is empty or null!!! <br>
    </c:if>

    <%-- iteration tag --%>
    <ul>
        <c:forEach items="${account.randomStrings}" var="arr">
            <li>${arr}</li>
        </c:forEach>
    </ul>

The <b>c:if tag</b> checks the condition within the <b>test</b> argument, here, we are checking whether <i>account.name</i> is empty (null or of zero len). The <b>test</b> is a defined format that must be specified.

The <b>c:forEach</b> iterates the Iterable in argument <b>items</b>, the <i>account.randomStrings</i> passed to this tag is a String Array, so it can be iterated. We define the reference of each element through the <b>var</b> parameter, so that we can print out each element. See <b>tag.jsp, Account.java and TagDemo.java in ElDemo for demo.</b>

We can also use <b>c:import tag</b> to include/import another JSP file as part of the current JSP, e.g.,:

    <body>
        <c:import url="_someheader.jsp"/>

        <!-- some other contents.... -->
    </body>

<b>c:url tag</b> is simlar to <b>a href="" tag</b>, both of them are used for navigation purpose. Sometimes, we want to navigate within our server or webapp as follows:

    <c:url value="/sigin.jsp"/>
    <a href="/signin.jsp">Sign In (Root URL)</a>
    <a href="/sigin.jsp">Sign In (Webapp Relative URL)</a>

With <b>"/"</b>, the url is refering to the root url as follows:

    "http://localhost:8080/someserver/signin.jsp"

Without <b>"/"</b>, the url is relative to the webapp as follows:

    "http://localhost:8080/someserver/webapp/signin.jsp"

However, with <b>c:url tag</b>, the created URL will always be webapp relative.

<h2>Writing Tag Libraries (skipped)</h2>

This section of content is skipped as JSTL and JSP is getting less relavant. It shouldn't be hard to pick up this old technology. I might just go for JAX-RS first.

<h2>Event Listeners</h2>

We use tag <b>@WebListener</b> to mark that a class as a listener. <b><i>Events</i> are fired at users at appropriate times, Events are methods called made by container.</b>

For example:

    - Application Events are fired at webapp start/end.
    - Session Events are fired at Session start/end.
    - Request Events are fired at Request start/end.
    - Attribute Events are fired when attribute added/removed.

<b>Listeners are created to listen for these events.</b>

<h3>To create a Listener</h3>

We need to implement appropriate interfaces, such as:

    - ServletContextListener
    - ServletContextAttributeListener
    - HttpSessionActivationListener
    - HttpSessionAttributeListener
    - ServletRequestListener
    - ServletRequestAttributeListener

And we <b>pass appropriate "Event" object to the associated Listener</b>

<h3>Register Listener</h3>

We register Listener in the <b>web.xml</b> file as follosw:

    <listener>
        <listener-class>
            com.curtisnewbie.SomeListener
        </listener-class>
    </listener>

Note that the methods in or these Listeners are executed by the container in the order of listing for activation, i.e., these Listeners are activated based on the order of listing in the web.xml file.

Further, these methods in or these Listeners are deactivated in the reverse order of listing in this web.xml file. I.e., the order of both activation and deactivation is based on the order of listing.

<h3>Application Listener</h3>

Application Listener is for <b>ServletContextEvents</b> that fired:

    at application start

    and

    at application end.

So, we need to implements the <b>interface ServletContextListener</b> as follows (See ListenerDemo maven project):

    public class AppListener implements ServletContextListener {

        // called just before servlet context being destroyed
        @Override
        public void contextDestroyed(ServletContextEvent sce) {

        }

        // called just after servlet context being initialised
        @Override
        public void contextInitialized(ServletContextEvent sce) {

        }
    }

However, these two methods are <b>default methods</b> in the interface, so we only need to implement the methods that we need. After creating the Listener classes, we need to register these Listeners, so that the containers know which listeners should be activated and deactivated. It will be in <b>web.xml</b> file.

    <listener>
        <listener-class>com.curtisnewbie.AppListener</listener-class>
    </listener>

When we access to this server, e.g., send a request, the appropriate methods will be called. Here is the demo of <b>ListenerDemo</b> project:

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("[" + new Date().toString() + "] Context Initialised");

        // we can also get initial context parameters from the context
        context.log("ContextName: " + context.getInitParameter("ContextName"));
    }

We override this method to log some message when the servlet context/webapp is initialised. When we access to any webpage in this server, the server log shows:

    10-Dec-2019 15:03:09.523 INFO [RMI TCP Connection(4)-127.0.0.1] org.apache.catalina.core.ApplicationContext.log [Tue Dec 10 15:03:09 GMT 2019] Context Initialised

    10-Dec-2019 15:03:09.523 INFO [RMI TCP Connection(4)-127.0.0.1] org.apache.catalina.core.ApplicationContext.log ContextName: Demos of Listener

<h3>Session Listener</h3>

Session Event is fired:

    after session start

    and

    after session ended.

So, for the event that is fired when session ended, no more session information can be accessed.

For HTTP specifically, the session event is <b>HttpSessionEvent</b>, and we need to implement <b>HttpSessionListener</b> or other interfaces associated, such as <b>HttpSessionAttributeListener</b> for attribute event in the http session.

In Demo <b>"ListenerDemo"</b>, we implements the HttpSessionListener, and overrides its methods as follows:

    // fired when a session is created
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String id = session.getId();
        Date createDate = new Date(session.getCreationTime());
        ServletContext servletContext = session.getServletContext();
        servletContext.log("Session Created:[id:" + id + " Date of Creation:" + createDate.toString());
    }

     // fired when a session is destroyed
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext servletContext = session.getServletContext();
        servletContext.log("Session Destroyed: Date:" + new Date().toString());
    }

When container creates/destroies a session, in the Server log:

    10-Dec-2019 15:35:47.606 INFO [http-nio-8080-exec-1] org.apache.catalina.core.ApplicationContext.log Session Created:[id:8DEAE562794982BBFD74C3C9964B81B2 Date of Creation:Tue Dec 10 15:35:47 GMT 2019

    10-Dec-2019 16:01:10.260 INFO [Catalina-utility-2] org.apache.catalina.core.ApplicationContext.log Session Destroyed: Date:Tue Dec 10 16:01:10 GMT 2019

Note that the session is destroyed by a background timer thread.

<h3>Request Listener</h3>

Request Event is fired:

    before request comes into scope (before it enters the first filter)

    and

    before request goes out of scope (after it's left the last the last filter)

The ServletRequestEvent gives access to:

    ServletContext and ServletRequest

In Demo <b>"ListenerDemo"</b>, we implements interface <b>ServletRequestListener</b> as follows:

    // called when it's destroyed
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("Request Destryoed.");
    }

    // called when it's initialised
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("Request Initialised.");
    }

After a simple GET request, in the server log:

        10-Dec-2019 15:47:36.095 INFO [http-nio-8080-exec-3] org.apache.catalina.core.ApplicationContext.log Request Initialised.

        10-Dec-2019 15:47:36.098 INFO [http-nio-8080-exec-3] org.apache.catalina.core.ApplicationContext.log Request Destryoed.

Note that, different from session, the request is destroyed when response is forwarded back to the client. However, session typicall last 20-30 mins. We can also adjust the <b>Session Timeout configuration in web.xml</b>

    <!--10 minutes -->
    <session-config>
        <session-timeout>10</session-timeout>
    </session-config>

<h3>Annotation and web.xml for Listeners</h3>

When creating Listeners for events, we need to mark them as Listeners so that the container will use them. There are two ways to do this, and only way should be selected for consistency:

    // Using Annotation in the Java class
    @WebListener

    // Using XML tags in web.xml
    <listener>
        <listener-class>path/to/java/file</listener-class>
    </listener>

<h2>Filters</h2>

<h3>What is a Filter</h3>

<b>Filter</b> can access to all requests, and do some extra processing on that those requests. It may or may not modify the requests and responses, that's why it's called Filter. Filters are executed before/after the request is executed, including the dispatcher's operation (<b>include and forward methods</b>). We can use Filters to provide:

    - Session Management
    - Logging
    - Security and
    - XML Transforms.

Filters can be executed as part of a <b>Chain</b>, i.e., there may be a number of Filters that comprise a Chain.

<h3>How Filters Work</h3>

Here is the example demonstrating how Filters (or chain of Filters) may work:

        request/response (maybe) manipulated at each filter, requests may be rejected for such as security or authorisation reason.


    ----->|    requests       |                         |
          |    ---------->    |                         |
          |                   |                         |
          |                   |       --------------->  |
          |                   |                         |
          |                   |       <---------------  |
          |   response        |                         |
    <-----|    <----------    |                         |
          |                   |                         |
    Filter1              Filter2                End Point
    (security)           (extra processing)

Note that the filters are called <b>synchronously</b>, so the chain is executed on one single thread.

<h3>To Write Filters</h3>

To create Filter class, we need to implement <b>Filter interface</b> and override its methods. The Filter acts quite similar to a Servlet in that it can process the request and forward response back to the client (e.g., rejecting the request before it gets to the end point).

Filter contains three important methods:

    init(){...}
    doFilter(){...}
    destroy(){...}

The <b>init() method</b> is only called once when the filter is loaded. The <b>doFilter() method</b> is called to do the processing and to dispatch the request to a different resource or return the caller without further chaining the request down to the next filter or end point. The <b>destroy() method</b> is only called once to destroy the filter.

<b>doFilter() method is like this:</b>

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

<b>Filter</b> similar to Servlet, may have:

    - the associated URL pattern
    - the associated name resources
    - the associated servlet

Filter can be configured web.xml file or annotated on the Java class, we only need to choose one way to configure it.

In <b>web.xml</b> file:

    <!--  Define filter name -->
    <filter>
        <filter-name>simpleFilter</filter-name>
        <filter-class>com.curtisnewbie.SimpleFilter</filter-class>
        <!-- We can give it initial parameters -->
        <init-param>
            <param-name>DBName</param-name>
            <param-value>MySql</param-value>
        </init-param>
    </filter>

    <!-- Map the filter to a url pattern or servlet -->
    <filter-mapping>
        <filter-name>simpleFilter</filter-name>
        <url-pattern>*</url-pattern>
        <!-- <servlet-name></servlet-name> -->
    </filter-mapping>

With <b>annotaion</b>:

    @WebFilter(urlPatterns="/someUrl")
    public class SomeFilter implements Filter {
        ...
    }

<h3> Demo "FilterDemo"</h3>

In Demo <b>"FilterDemo"</b>, the way to implement the Filter is shown.

<b>init() method</b> is called when the filter is loaded. In <b>init()</b> method, we can instantiate some settings or variables by getting the information or parameters we need from the <b>FilterConfig</b> object as follows:

    // called once when this filter is loaded
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // we can get various information/config using this method
        if (filterConfig != null) {
            // store it as instance variable for later use
            config = filterConfig;

            // use FilterConfig to get various useful information
            ServletContext context = filterConfig.getServletContext();
            filterName = filterConfig.getFilterName();
            String iniParamOfDB = filterConfig.getInitParameter("DBName");
        }
    }

In <b>doFilter()</b> method, we will undertake some operations, such as verifying authorisation or pre-processing of data, etc., then we will need to pass the request and response to the next Filter or end point. In order to pass the request and response further down to the next filter or to the end point (the resource), we do the following operation:

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // do some pre-processing
        .......

        // chain the filter
        chain.doFilter(request, response);
    }

According to the following source:

    "Servlet Filters are an implementation of the Chain of responsibility design pattern.

    All filters are chained (in the order of their definition in web.xml). The chain.doFilter() is proceeding to the next element in the chain. The last element of the chain is the target resource/servlet."

    src: https://stackoverflow.com/questions/2057607/what-is-dofilter-doing-in-dofilter-method-in-filters-of-java

So, this method is essentially chaining the request to the next filter or the resources.

In <b>destroy()</b> method, the filter is being destroyed, this is a default method. It by default takes no action, but we can use it to know when the filter is destroyed, etc.

    @Override
    public void destroy() {
        logger.info("Destroyed: FilterName:" + filterName);
    }

Most importantly, we need to configurat the filter class using either annotation or web.xml:

    <!--  Define filter name -->
    <filter>
        <filter-name>LogFilter</filter-name>
        <filter-class>com.curtisnewbie.LogFilter</filter-class>
        <!-- We can give it initial parameters -->
        <init-param>
            <param-name>DBName</param-name>
            <param-value>MySql</param-value>
        </init-param>
    </filter>

    <!-- Map the filter to a url pattern or servlet -->
    <filter-mapping>
        <filter-name>LogFilter</filter-name>
        <url-pattern>*</url-pattern>
    </filter-mapping>

<h3>Log4j2</h3>

As shown in demo <b>"FilterDemo"</b>, the <i>"LogFilter.java"</i> acts as filter that logs the request of resources. This demo uses the <b>Log4j2</b>. The maven dependency is as follows:

    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.12.1</version>
    </dependency>

To use Log4j, we need to configure where and how the information is logged, there are various way to provide configuration. Here, we create file <b>"main/resources/log4j2.xml"</b>, and configure it as follows. The log information is output to a file named <b>"application.log"</b>.

    <?xml version="1.0" encoding="UTF-8"?>
    <!--configuration file for log4j2-->
    <Configuration status="debug" name="LoggingConfig">
        <Appenders>
            <File name="File" fileName="/home/yongjie/tomcat9/apache-tomcat-9.0.29/logs/application.log">
                <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
            </File>
        </Appenders>
        <Loggers>
            <Root level="info">
                <AppenderRef ref="File"/>
            </Root>
        </Loggers>
    </Configuration>

In the filter class, we first create the static instance of Logger:

    static Logger logger = LogManager.getLogger(LogFilter.class);

Log4j2 has 7 levels of information defining the severity of information, and each provides different levels of details:

    - OFF
    - FATAL
    - ERROR
    - WARN
    - INFO
    - DEBUG
    - TRACE

    Src: https://en.wikipedia.org/wiki/Log4j

In demo, we log <b>INFO</b> level of info as follows:

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // use log4j2 to log this information. Info is a specific level of information (there are 7 levels)
        logger.info("request made to '" + ((HttpServletRequest) request).getRequestURI() + "'");
        // chain the filter
        chain.doFilter(request, response);
    }

After making request to the webapp, in the <b>"application.log"</b>:

    21:39:08.967 [RMI TCP Connection(2)-127.0.0.1] INFO  com.curtisnewbie.LogFilter - Filter initialised: FilterName:LogFilter, DBName:MySql

    21:39:09.493 [http-nio-8080-exec-1] INFO com.curtisnewbie.LogFilter - request made to '/filterdemo_war/'

    21:39:10.374 [http-nio-8080-exec-3] INFO com.curtisnewbie.LogFilter - request made to '/filterdemo_war/'

<h2>Wrapping Request and Response</h2>

<h3>Wrapping Request - Passing Logger to The Wrapper Class</h3>

We can use Wrapper class to wrap the <b>Servlet Request</b>. We can wrap a request in the Filter so that we can still have access to it after sending it further down the chain, we can log information of this request by adding a logging operation in this class, and pass this object further down the chain, so every time a method is called, the logger records this information.

The Wrapper is as follows:

    public class LogHttpRequestWrapper extends HttpServletRequestWrapper {

        private Logger logger;

        public LogHttpRequestWrapper(HttpServletRequest request, Logger logger) {
            super(request);
            this.logger = logger;
        }

        @Override
        public String getHeader(String name) {
            logger.info("getHeader() being called");
            return super.getHeader(name);
        }
    }

<b>This wrapper is instantiated wrapping the request object in the doFilter() method. We pass the logger into this wrapper when we wrap a request, the logger then can be used to log information we need, even after passing this object down the chain.</b>

For example, here, we overide the <i>getHeader() method</i> and add the logging operation in it, so that every time the method is called, information is logged. When the <b>getHeader() method</b> is called, the logger log information as below:

    22:12:19.360 [http-nio-8080-exec-4] INFO com.curtisnewbie.LogFilter - getHeader() being called

<h3>Wrapping Response - Compressing Response Data</h3>

We can also create a Wrapper to wrap the <b>Servlet Response</b>. As mentioned above, the filters, request and response can be considered as a chain. The filters can intercepts requests and do some pre-processing before the requests and responses reach the end point. The filters can also be used to wrap responses, so that post-processing is undertaken.

In demo <b>FilterDemo</b>, a Filter (<b>CompressFilter</b>) is created that create a response wrapper and pass the wrapper down the chain. It is as follows:

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            String acceptGzip = ((HttpServletRequest) request).getHeader("accept-encoding");

            if (acceptGzip != null && acceptGzip.indexOf("gzip") != -1) {

                // compress output stream by using a response wrapper
                GzipHttpResponseWrapper compressResponseWrapper =
                        new GzipHttpResponseWrapper((HttpServletResponse) response);

                // pass wrapper down the chain
                chain.doFilter(request, compressResponseWrapper);

                // set content type so that client know the response content is compressed.
                compressResponseWrapper.addHeader("Content-Encoding", "gzip");

                // after the request is processed (or the chain is finished), close stream and writer
                compressResponseWrapper.closeResponse();
                return;
            }
        }

        // if not http servlet response or not accepting compression, pass original request and response objects
        chain.doFilter(request, response);
    }

In <b>doFilter() method</b>, we first instantiate a wrapper class of normal <b>HttpServletResponse</b> object, then we pass this wrapper object down the chain by calling <b>chain.doFilter() method</b>, when the chain is finished (response being forwarded back to the client), we <b>close the response (i.e., close the internal Output Stream)</b>. The closeResponse() method is just a normal method used to close the streams. <b>If the client do not accept encoding or using gzip, we simply pass the original response object without using any wrapper.</b>

In the wrapper class (<b>GzipHttpResponseWrapper</b>) which is an subclass of <b>HttpServletResponse</b>, we override the HttpServletResponse's methods (such as getWriter() and getOutputStream() methods), so that the following writers or output streams that are used in the methods such as doGet() or doPost() in servlets are wrapped GzipOutputStream. For example:

    private ServletOutputStream gzipOut = null;
    private PrintWriter gzipWriter = null;

     @Override
    public PrintWriter getWriter() throws IOException {
        if (gzipOut == null) {
            gzipOut = createGzipOutputStream();
        }

        if (gzipWriter == null) {
            gzipWriter = new PrintWriter(gzipOut);
        }
        return gzipWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (gzipOut == null) {
            gzipOut = createGzipOutputStream();
        }
        return gzipOut;
    }

    @Override
    public void flushBuffer() throws IOException {
        gzipOut.flush();
    }

    /**
     * Create Gzip ServeltOutputStream
     */
    private ServletOutputStream createGzipOutputStream() throws IOException {
        return new GzipServletOutputStream(originalResponse.getOutputStream());
    }

The one above is the wrapper for <b>HttpServletResponse</b>. However, we also need a wrapper for the <b>ServletOutputStream</b> because the jdk built-in class <b>"java.util.zip.GZIPOutputStream"</b> is not a ServletOutputStream, it is simply a subclass of OutputStream for compression. We need to create a custom claas that extends <b>ServletOutputStream</b>, and use the GZIPOutputStream object internally as follows:

    public class GzipServletOutputStream extends ServletOutputStream {

        private GZIPOutputStream gzipOut;

        public GzipServletOutputStream(OutputStream outputStream) throws IOException {
            super();
            this.gzipOut = new GZIPOutputStream(outputStream);
        }

        ...

        @Override
        public void write(int i) throws IOException {
            gzipOut.write(i);
        }

        @Override
        public void write(byte[] b) throws IOException {
            gzipOut.write(b);
        }
    }

For the configuration part in <b>web.xml</b> (can also be using annotaions):

    <filter>
        <filter-name>CompressFilter</filter-name>
        <filter-class>com.curtisnewbie.CompressFilter</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>CompressFilter</filter-name>
        <url-pattern>*.gzip</url-pattern>
    </filter-mapping>

It means we uses this "compression" filter for any url with a suffix of ".gzip". We can see the difference between normal response and compressed response in the browser. However, the content-length is not claimed in the response header (compressed one), and it can be unreliable for compressed response.

    // For normal response without gzip encoding:

        Connection: keep-alive
        Content-Type: text/html;charset=utf-8
        Date: Thu, 12 Dec 2019 14:02:22 GMT
        Keep-Alive: timeout=20

    // For response with gzip encoding:

        Connection: keep-alive
        Content-Encoding: gzip
        Content-Length: 239
        Content-Type: text/html;charset=utf-8
        Date: Thu, 12 Dec 2019 14:02:30 GMT
        Keep-Alive: timeout=20

<h3>Summary Of Filters</h3>

After Servlet 2.4, we can use Filter for dispatcher methods not just the incoming requests from the clients.

    <filter-mapping>
        <filter-name>someFilter</filter-name>
        <servlet-name>servletName</servlet-name>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>

We can also use annotations to configure the filters similart to that for servlet, such as:

    @WebFilter
    - name
    - urlPatterns
    - dispatcherType
    - servletNames
    - asyncSupported
    - ...

    @WebInitParam
    ...

For example:

    @WebFilter("/someUrl")
    public class ExampleFilter implements Filter {...}

    @WebFilter(urlPatterns = "*.gzip")
    public class ExampleFilter implements Filter {...}

    @WebFilter(servletName = {"LoggingServlet", "AuthorisationServlet"})
    public class ExampleFilter implements Filter {...}

    @WebFilter(
        urlPatterns = "*.gzip"
        initParams = @WebInitParam(name="fileTypes", value="txt"))
    public class ExampleFilter implements Filter {...}

    @WebFilter(
        urlPatterns = "*.gzip"
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
    public class ExampleFilter implements Filter {...}

<h2>Asynchronous Programming</h2>

Both Servlets and Filters can be asynchronous. Asynchronous programming is used for:

    - Slow Backend Resources (e.g., for clients to download files)
    - Resuse of Threads (i.e., offload and resuse the threads for communicating with
        clients, and create threads for heavy processing in the background)
    - Server Push (long pulling services, e.g., for ajax request)

<h3>How Async Servlet Works?</h3>

We generally follows this order:

    1. Mark servlet/filter as asynchronous
    2. Start the async context to handle requests (push requests
        to different threads for managing requests)
    3. Use async context to return the response back to the clients when the work is done

<h4>1.Mark servlet/filter as asynchronous</h4>

We use annotations or configure in web.xml to show that it's asynchronous:

    // for annotation
    @WebServlet(urlPatterns="/home", asyncSupported = true)
    public class HomeServlet extends HttpServlet{

    }

    <!-- for web.xml -->
    <servlet>
        <servlet-name>servletName</servlet-name>
        <servlet-class>servletClass</servlet-class>
        <async-supported>true</async-supported>>
    </servlet>

<h4>2.Get Async Context</h4>

We can get the <b>AsyncContext object</b> as follows:

    // for example in doGet method
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        final AsyncContext asynCtx = req.startAsync();

    }

<h4>3. Start and Complete Async Work</h4>

Similar to Thread, we call <b>.start() method</b> and pass a <b>Runnable object</b> in it to start the async work. The container will handle these threads.

    asynCtx.start(() ->{

        try{
           // do some thing
           asynCtx.getResponse().getWriter().write(.....)
        }catch(IOException e){
            // log excpetion msg at an error level (log4j2)
            logger.info(Level.ERROR, e.toString);
        }

        // and then we mark it as completed
        asynCtx.complete();
    });

If we don't want to handle this request within the servlet, we can simply dispatch to another servlet:

    final AsyncContext asynCtx = req.startAsync();
    asynCtx.dispatch("/otherUrl");
