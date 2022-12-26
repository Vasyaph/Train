package com.my;

import clases.Carriage;
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

@WebServlet("/TicketInfo")
public class TicketInfo extends HttpServlet {
	public UsersFunctions usersFunctionsMain = new UsersFunctions();
	public TrainFunctions trainFunctionsMain = new TrainFunctions();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int ticketId = Integer.parseInt(req.getParameter("ticketId"));


		User user = (User) session.getAttribute("user");
		UsersFunctions usersFunctions = usersFunctionsMain;
		Ticket ticket = usersFunctions.getTicketById(ticketId);
		req.setAttribute("ticket", ticket);

		TrainFunctions trainFunctions = trainFunctionsMain;
		Carriage carriage = trainFunctions.getCarriagesById(ticket.getCarriageId());
		System.out.println("Carriage id:"+carriage.getId());
		Path path = trainFunctions.getPathByCarriageId(carriage.getId());
		System.out.println("patch id:"+path.getId());


		req.setAttribute("carriage", carriage);
		req.setAttribute("paths", path);
		req.getRequestDispatcher("/ticketInfo.jsp").forward(req, resp);
	}
}
