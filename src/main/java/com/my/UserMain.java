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

@WebServlet("/allPath")
public class UserMain extends HttpServlet {
	public TrainFunctions trainFunctionsMain = new TrainFunctions();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try {

			int count = Integer.parseInt(req.getParameter("counter"));

			List<Path> allPatchs = (List<Path>) req.getSession().getAttribute("allPaths");

			List<Path> outPaths2 = new ArrayList<>();
			int limit = 0;
			if (allPatchs.size() > count + 8)
				limit = count + 8;
			else
				limit = allPatchs.size();
			for (int i = count; i < limit; i++) {
				outPaths2.add(allPatchs.get(i));
			}


			req.setAttribute("counter", count);
			req.setAttribute("paths", outPaths2);
			if (req.getSession().getAttribute("user") != null)
				getServletContext().getRequestDispatcher("/userMain.jsp").forward(req, resp);
			if (req.getSession().getAttribute("admin") != null)
				getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);

		} catch (NumberFormatException | NullPointerException e) {

			TrainFunctions trainFunctions = trainFunctionsMain;
			List<Path> paths = trainFunctions.GetAllPath();
			List<Path> outPaths = new ArrayList<>();
			String departure = req.getParameter("departure");
			String arrival = req.getParameter("arrival");
			String departureT = req.getParameter("departureT");

			if (departureT == null) {
				if (req.getSession().getAttribute("user") != null) {
					req.getRequestDispatcher("/userMain.jsp").forward(req, resp);
					return;
				}
				if (req.getSession().getAttribute("admin") != null) {

					req.getRequestDispatcher("/adminMain.jsp").forward(req, resp);
					return;
				}
			}

			int now_ticket = 0;
			if (req.getParameter("scroll") != null) {
				now_ticket = Integer.parseInt(req.getParameter("scroll"));
			}

			try {
				Date d = Date.valueOf(departureT);
			} catch (IllegalArgumentException ex) {
				if (req.getSession().getAttribute("user") != null) {
					getServletContext().getRequestDispatcher("/userMain.jsp").forward(req, resp);
					return;
				} else if (req.getSession().getAttribute("admin") != null) {
					getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
					return;
				}
			}


			paths = paths.stream().filter(x -> (new Date(x.getDispatch_time().getTime())).toString().equals(departureT)).collect(Collectors.toList());


			for (Path path :
					paths) {
				if ((path.getArrival().getLocation().equals(arrival)) && (path.getDispatch().getLocation().equals(departure))) {
					outPaths.add(path);
				}
			}


			String selectPlaceT = req.getParameter("placeT");

			Carriage timeCar = new Carriage(selectPlaceT);
			outPaths = outPaths.stream().filter(x -> x.getCarriages().contains(timeCar)).collect(Collectors.toList());
			List<Path> outPaths2 = new ArrayList<>();
			int limit = 0;
			if (outPaths.size() > now_ticket + 8) {
				limit = now_ticket + 8;
			} else {
				limit = outPaths.size();
			}
			for (int i = now_ticket; i < limit; i++) {
				outPaths2.add(outPaths.get(i));
			}

			req.setAttribute("counter", now_ticket);
			req.setAttribute("paths", outPaths2);
			req.getSession().setAttribute("allPaths", outPaths);
			if (req.getSession().getAttribute("user") != null) {
				req.getRequestDispatcher("/userMain.jsp").forward(req, resp);
				return;
			}
			if (req.getSession().getAttribute("admin") != null) {
				req.getRequestDispatcher("/adminMain.jsp").forward(req, resp);
				return;
			}


		} catch (Exception e) {

			if (req.getSession().getAttribute("user") != null)
				getServletContext().getRequestDispatcher("/userMain.jsp").forward(req, resp);
			else if (req.getSession().getAttribute("admin") != null)
				getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
			else
				getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		}

	}
}
