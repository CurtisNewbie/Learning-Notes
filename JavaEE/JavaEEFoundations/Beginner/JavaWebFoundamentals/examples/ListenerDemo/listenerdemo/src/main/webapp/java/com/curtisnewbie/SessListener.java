package com.curtisnewbie;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletContext;
import java.util.*;

public class SessListener implements HttpSessionListener {

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
}
