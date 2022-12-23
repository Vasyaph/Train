package com.my;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeLanguage")
public class ChangeLanguage extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        String newLan=req.getParameter("lang");
        session.setAttribute("language",newLan);
        if(session.getAttribute("user")!=null)
           req.getRequestDispatcher("/userProf.jsp").forward(req, resp);
        if(session.getAttribute("admin")!=null)
           req.getRequestDispatcher("/adminProf.jsp").forward(req, resp);

    }
}
