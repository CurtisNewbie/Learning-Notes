package com.curtisnewbie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String veg1 = req.getParameter("veg1");
        String veg2 = req.getParameter("veg2");
        String veg3 = req.getParameter("veg3");

        String[] veggie = null;
        if (veg1 != null && veg2 != null && veg3 != null) {
            veggie = new String[3];
            veggie[0] = veg1;
            veggie[1] = veg2;
            veggie[2] = veg3;
        }

        if(name != null && veggie != null){
            // construct a bean
            User user = new User();
            user.setName(name);
            user.setVeggie(veggie);

            // dispatch bean to login.jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
            req.setAttribute("user", user);
            dispatcher.forward(req, resp);
        }else{
            resp.sendRedirect("index.jsp");
        }
    }
}
