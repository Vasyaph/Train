package com.my;

import clases.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/PaymentVerification")
public class PaymentVerification extends HttpServlet {
	private static final Logger log = LogManager.getLogger(PaymentVerification.class);

	public Ticket ticketMain = new Ticket();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			HttpSession session = req.getSession();

			User user = (User) session.getAttribute("user");
			Carriage carriage = (Carriage) session.getAttribute("carriage");
			int place = (int) session.getAttribute("place");

			Ticket ticket = ticketMain;
			ticket.setCarriageId(carriage.getId());
			ticket.setPlace(place);
			ticket.setPersonId(user.getId());


			ticket.setName(req.getParameter("Name"));
			ticket.setSurname(req.getParameter("Lastname"));

			if (ticket.AddTicket()) {
				session.removeAttribute("carriage");
				session.removeAttribute("place");
				Path path = (Path) req.getSession().getAttribute("paths");

				session.removeAttribute("paths");
				user.AddTickets(ticket);
				session.setAttribute("user", user);
				log.info("user " + user.getId() + " by " + ticket.getId() + " ticket");

				long rl = path.getReturnPatch();
				if (rl != 0) {
					req.setAttribute("patchID", rl);
					RequestDispatcher rd = req.getRequestDispatcher("/returnTicket.jsp");
					rd.forward(req, resp);
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/tickets.jsp");
					rd.forward(req, resp);
				}

			}
		} catch (NullPointerException exception) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/tickets.jsp");
			rd.forward(req, resp);
		}


	}
}
