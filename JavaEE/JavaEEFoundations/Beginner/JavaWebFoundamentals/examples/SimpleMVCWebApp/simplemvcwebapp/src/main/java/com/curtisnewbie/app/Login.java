package com.curtisnewbie.app;

import com.curtisnewbie.sampledata.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * This servlet takes care of the login page, it redirects the response based on
 * whether the given credentials are correct. If not, the response is redirected
 * back to the index.jsp where the user needs to provide login credentials. If
 * yes, this page redirect to account.jsp.
 *
 * Here, We use sample data stored in sampledata/SampleData.java
 *
 * @author yongjie
 */
public class Login extends HttpServlet {

    private static final long serialVersionUID = -3353907269786495916L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pw = req.getParameter("password");
        Account account = null;

        if (name == null || name.isEmpty() || pw == null || pw.isEmpty()) {
            // for illegal login credentials, redirect it to index.jsp
            resp.sendRedirect("index.jsp");
            return;
        }

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

        if (account == null) {
            // for incorrect credentials
            resp.getWriter().write("Incorrect Credential " + name + " " + pw);
            // resp.sendRedirect("/index.jsp");
        } else {
            // we put this account bean to the request scope, we dispatch it to the
            // account.jsp.
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/account.jsp");
            req.setAttribute("account", account);
            // it can either forward or include, where forward means we have done
            // processing, and include means we still need to get the request and response
            // back for further processing.
            dispatcher.forward(req, resp);
        }
    }
}
