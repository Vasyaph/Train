package com.my;

import clases.Carriage;
import clases.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ConfirmTicket")
public class ConfirmTicket extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int path_id = Integer.parseInt(req.getParameter("id"));
		int carriageNumber = Integer.parseInt(req.getParameter("carriage"));
		TrainFunctions trainFunctions = new TrainFunctions();
		Path path = trainFunctions.getPathById(path_id);
		session.setAttribute("paths", path);
		Carriage carriage = path.getCarriages().get(carriageNumber - 1);
		session.setAttribute("carriage", carriage);
		int place = Integer.parseInt(req.getParameter("place"));
		session.setAttribute("place", place);


		getServletContext().getRequestDispatcher("/ConfirmT.jsp").forward(req, resp);
	}
}
