package com.curtisnewbie;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

public class ReqListener implements ServletRequestListener {
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
}
