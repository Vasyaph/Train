package com.my;


import clases.Path;
import clases.Ticket;
import clases.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ticketsPage")
public class TicketPage extends HttpServlet {
	UsersFunctions usersFunctions=new UsersFunctions();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("user");
		usersFunctions.searchTicketByUser(user);

		List<Ticket> tickets = user.getTickets();
		List<Path> paths = new ArrayList<>();
		TrainFunctions trainFunctions = new TrainFunctions();
		for (Ticket ticket : tickets) {
			paths.add(trainFunctions.getPathByCarriageId(ticket.getCarriageId()));
		}


		req.setAttribute("paths", paths);

		getServletContext().getRequestDispatcher("/tickets.jsp").forward(req, resp);
	}
}
