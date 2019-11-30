# Java Web Fundamentals

Main Contents:

    - Servlets
    - Java ServerPages
    - Filters for compressing data
    - Event handlers

<h2>Java Web Programming</h2>

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

In the directory of the Tomcat server, there is a folder named <b><i>"webapps"</i></b>
which contains (or hosts) the web applications.

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

A webapp is usually packaged as a .war file or Web Archive file for
deployment. Wherein there are a number of folders, such as the folder for
resources (e.g., images), the WEB-INF folder, the META-INF folder and so on.

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

In Tomcat, there is a default webapp called "manager" that allows you to
manage the server using a GUI interface. However, to use it, configuration
such as roles and user credentials need to be setup in <b>"\$CATALINA_HOME/
conf/tomcat-users.xml"</b>.

    <role rolename="manager-gui"/>
    <user username="someuser" password="somepassword" roles="manager-gui"/>

More specifically, these roles are specified in <b>"/\$CATALINA_HOME/webapps/
manager/WEB-INF/web.xml"</b>

About manager webapp, see:

    <https://tomcat.apache.org/tomcat-9.0-doc/manager-howto.html>

Manager webapp is useful in that it allows the admin to undertake certain
managing operations on the server, such as upload, reload, or stop the
deployed webapps. Further <b>Maven</b> can be used in conjunction with Tomcat
(or more specifically Manager webapp) to facilitate the webapp deployment.

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

The <i>url</i> tag is a uniform one that doesn't need to be changed unless the
remote deployment is needed. <i>server</i> tag refers to the id of the server,
which is speicified in maven's setting in <b>"\$MAVEN_HOME/conf/settings.xml"
</b>, see below. The <i>path</i> tag is used to specify the URL Base of the
webapp, i.e., where you want to deploy it in the webapps directory in Tomcat.

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

<h2>Servlets</h2>

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

These methods are corresponding to the request and methods specified in HTTP, such as Get and Post. In service() method, we use getMethod() to check what the request is, and then we repond to such request by calling the correct methods, such as doGet(), doPost() or doPut().

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

To implement a servlet for http protocol, we extends from the HttpServlet and overides its methods for the servlet lifecyle and the helper methods (e.g., doGet() and doPost()). However, without <b>Servlet Mapping</b>, we cannot access to this servlet. We need to add an annotation <b><i>"@WebServlet("/home")"</i></b>, so that we can access this servlet through the url "https://server/home".

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

In the example, FirstServlet project, the packaged .war file will be named exactly as the artifactId
specified in pom.xml file. (e.g., "firstservlet.war"). We then deploy this .war file using the
"manager" webapp in tomcat. The URL base for this servlet will be as follows:

    "https://localhost:8080/firstservlet/home"

<b>"/firstservlet"</b> is the name of the webapp, or the artifactId.<br>
<b>"/home"</b> is the base url of this servlet, which is specified using
<i><b>"@WebServlet("/home")"</b></i> annotaion.

<h2>Request Routing</h2>

Server routes request to servlet using configuration information, which is held in <b>web.xml</b>.
One servlet instance handles all requests to the associated url-pattern, not just one single base url, see below.

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

As shown in previous "firstservlet" example, the base url can be mapped to a servlet by the
annotation "@WebServlet", it can also be mapped based on the configuration in web.xml. As in above
example, the class <i>MappingDemo</i> is given a name called <i>DemoServlet</i>, and this
servlet is mapped to url patterns

    "*.demo" and "/demo"

I.e., any url request like:

    "https://localhost:8080/servletmapping/demo"

or

    "https://localhost:8080/servletmapping/asdfasdf.demo"

will be mapped to this servlet, executed by the class "MappingDemo". It is worth noting that tag,<b><i>servlet-name</i></b> is used in web.xml, rather than in the base url.

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

After this line, the first part is the Request Headers that specify the user-agent,
accept-type, accept-language and so on. The second part is the for the general purpose that apply to the message as a whole. The third part is the Entity Header, that apply to the body of the request, such as the Content-Length.

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

Further, in the above example of HttpServletRequest, parameters in a HTTP request can be
extracted. <b>Parameters</b> are variables given and specified in the Http URL after the ?
question mark, or if the request has a body, parameters can be from the body (such as
from a form).

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

In <b>doGet()</b> method, we do a simple request and response handling, that it gets "name" parameter from the request, and we print out a message and respond to the
client. The URL can be as follow.

    "http://localhost:8080/reqrep/react?name=curtis"

Wherein, <i>"reqrep"</i> is the name of the webapp, <i>"/react"</i> is the url-pattern
mapped to a servlet, and <i>"?name=curtis"</i> is a parameter in this request. If not
parameter provided or this URL doesn't formatted correctly (e.g., without ?), the method
<b>getParameter("name")</b> will simply return null just like no parameter provided.

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
