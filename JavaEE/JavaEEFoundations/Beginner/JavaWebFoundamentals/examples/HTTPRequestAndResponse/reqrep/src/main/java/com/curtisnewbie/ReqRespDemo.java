package com.curtisnewbie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReqRespDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get parameter name (case-sensitive)
        String name = req.getParameter("name");

        PrintWriter out = resp.getWriter();

        if (name != null) {
            out.printf("Hello %s :D", name);
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // get parameter name (case-sensitive) from the form
        String name = req.getParameter("name");

        if (name != null) {
            // redirect it to the login.jsp
            resp.sendRedirect("login.jsp");
        }
    }
}