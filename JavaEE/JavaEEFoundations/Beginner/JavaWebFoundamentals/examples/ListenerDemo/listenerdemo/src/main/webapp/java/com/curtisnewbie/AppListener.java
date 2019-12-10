package com.curtisnewbie;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class AppListener implements ServletContextListener {

    // called just before servlet context being destroyed
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // get the ServletContext
        ServletContext context = sce.getServletContext();
        // then we can use the methods defined in this context to do various things, such as to log some info.
        context.log("[" + new Date().toString() + "] Context Destroyed");
    }

    // called just after servlet context being initialised
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.log("[" + new Date().toString() + "] Context Initialised");

        // we can also get initial context parameters from the context
        context.log("ContextName: " + context.getInitParameter("ContextName"));
    }
}
