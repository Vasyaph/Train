package com.my;

import clases.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buyTicket")
public class BuyTicket extends HttpServlet {
	public TrainFunctions trainFunctionsMain=new TrainFunctions();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int path_id = Integer.parseInt(req.getParameter("id"));
		TrainFunctions trainFunctions =trainFunctionsMain;
		Path path = trainFunctions.getPathById(path_id);
		req.setAttribute("paths", path);
		if (req.getSession().getAttribute("user") != null)
			req.getRequestDispatcher("/buyTicket.jsp").forward(req, resp);
		if (req.getSession().getAttribute("admin") != null)
			req.getRequestDispatcher("/buyTicketA.jsp").forward(req, resp);


	}
}
