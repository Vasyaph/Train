package com.my;

import clases.Carriage;
import clases.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/allPathA")
public class AdminMain extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try {
			Integer count = Integer.parseInt(req.getParameter("counter"));

			List<Path> allPaths = (List<Path>) req.getSession().getAttribute("allPaths");
			List<Path> outPaths2 = new ArrayList<>();
			int limit = 0;
			if (allPaths.size() > count + 8)
				limit = count + 8;
			else
				limit = allPaths.size();
			for (int i = count; i < limit; i++) {
				outPaths2.add(allPaths.get(i));
			}
			req.setAttribute("counter", count);
			req.setAttribute("paths", outPaths2);
			getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
		} catch (NumberFormatException e) {

			TrainFunctions TrainFunctions = new TrainFunctions();
			List<Path> paths = TrainFunctions.GetAllPath();
			List<Path> outPaths = new ArrayList<>();
			String departure = req.getParameter("departure");
			String arrival = req.getParameter("arrival");
			String departureT = (req.getParameter("departureT"));
			int now_ticket = 0;
			if (req.getParameter("scroll") != null) {
				now_ticket = Integer.parseInt(req.getParameter("scroll"));
			}
			try {


				Date d = Date.valueOf(departureT);
			}catch (IllegalArgumentException ex){
				getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
			}

			Date c = new Date(paths.get(0).getDispatch_time().getTime());


			paths = paths.stream().filter(x -> (new Date(x.getDispatch_time().getTime())).toString().equals(departureT)).collect(Collectors.toList());

			for (Path path :
					paths) {

			}
			for (Path path :
					paths) {
				if ((path.getArrival().getLocation().equals(arrival)) && (path.getDispatch().getLocation().equals(departure))) {
					outPaths.add(path);
				}
			}

			String[] selectedcurriage = req.getParameterValues("carriage");
			String selectPlaceT = req.getParameter("placeT");
			Carriage timeCar = new Carriage(selectPlaceT);
			outPaths = outPaths.stream().filter(x -> x.getCarriages().contains(timeCar)).collect(Collectors.toList());
			List<Path> outPaths2 = new ArrayList<>();
			int limit = 0;
			if (outPaths.size() > now_ticket + 8)
				limit = now_ticket + 8;
			else
				limit = outPaths.size();
			for (int i = now_ticket; i < limit; i++) {
				outPaths2.add(outPaths.get(i));
			}

			req.setAttribute("counter", now_ticket);
			req.setAttribute("paths", outPaths2);
			req.getSession().setAttribute("allPaths", outPaths);
			getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
			paths.get(1).getDispatch_time();

		} catch (NullPointerException e) {
			getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
		}

	}
}