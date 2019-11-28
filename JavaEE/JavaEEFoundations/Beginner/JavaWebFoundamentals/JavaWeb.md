# Java Web Fundamentals

Main Contents:

    - Servlets
    - Java ServerPages
    - Filters for compressing data
    - Event handlers

<h3>Java Web Programming</h3>

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
    <dependency>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.2</version>
        <configuration>
            <url>http://localhost:8080/manager/text</url>
            <server>IdOfServer</server>
            <path>/myappurl</path>
        </configuration>
    </dependency>

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

About maven deployment setup, see:

    http://tomcat.apache.org/maven-plugin-2.2/
    https://www.baeldung.com/tomcat-deploy-war
    https://mvnrepository.com/artifact/org.apache.tomcat.maven/tomcat7-maven-plugin/2.2
