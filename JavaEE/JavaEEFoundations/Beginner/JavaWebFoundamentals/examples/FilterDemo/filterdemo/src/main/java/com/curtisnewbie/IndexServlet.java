package com.curtisnewbie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple Servlet for index.jsp, it's used to test GZIP response compression
 */
@WebServlet(urlPatterns = "*.gzip")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        PrintWriter writer = resp.getWriter();
//        writer.write("<b>You are using gzip compression</b>");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
