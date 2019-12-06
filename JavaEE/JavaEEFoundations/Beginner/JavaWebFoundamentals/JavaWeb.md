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

<h2>MVC Architecture</h2>

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
