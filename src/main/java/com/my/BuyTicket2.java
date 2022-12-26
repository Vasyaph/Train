package com.my;

import clases.Carriage;
import clases.Path;
import clases.PlaseBull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/buyTicket2")
public class BuyTicket2 extends HttpServlet {
	public TrainFunctions trainFunctionsMain=new TrainFunctions();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int path_id = Integer.parseInt(req.getParameter("id"));
		int carriageNumber = Integer.parseInt(req.getParameter("carriage"));
		TrainFunctions trainFunctions = trainFunctionsMain;

		Path path = trainFunctions.getPathById(path_id);
		req.setAttribute("paths", path);
		Carriage carriage = path.getCarriages().get(carriageNumber - 1);
		req.setAttribute("carriage", carriage);

		List<PlaseBull> arrayPlace = new ArrayList<>();
		for (int i = 0; i < carriage.getNumber_of_seats(); i++) {
			arrayPlace.add(new PlaseBull());
			arrayPlace.get(i).setPlace(i + 1);
			arrayPlace.get(i).setFree(carriage.isFree(i + 1));
		}
		req.setAttribute("places", arrayPlace);

		if (req.getSession().getAttribute("user") != null)
			req.getRequestDispatcher("/buyTicket2.jsp").forward(req, resp);
		if (req.getSession().getAttribute("admin") != null)
			req.getRequestDispatcher("/buyTicket2A.jsp").forward(req, resp);


	}
}
