package com.curtisnewbie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/tag")
public class TagDemo extends HttpServlet {

    // we use GET here for convenience
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String money = req.getParameter("money");
        String age = req.getParameter("age");

        Account account = new Account();
        account.setName(name);
        account.setMoney(money == null ? -1 : Double.parseDouble(money));
        account.setAge(age == null ? -1 : Integer.parseInt(age));
        account.setRandomStrings(new String[]{"apple","banan","doc"});

        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/tag.jsp");
        req.setAttribute("account", account);
        dispatcher.forward(req, resp);
    }
}
