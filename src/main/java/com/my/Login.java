package com.my;

import clases.Admin;
import clases.Station;
import clases.Train;
import clases.User;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/enter")
public class Login extends HttpServlet {
	private static final Logger log = LogManager.getLogger(Login.class);
	public TrainFunctions trainFunctionsMain=new TrainFunctions();
	public UsersFunctions usersFunctionsMain = new UsersFunctions();
	public AdminFunction adminFunctionMain = new AdminFunction();



	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		if (session.getAttribute("user") != null) {
			req.getRequestDispatcher("/userMain.jsp").forward(req, resp);
		} else if (session.getAttribute("admin") != null) {
			req.getRequestDispatcher("/adminMain.jsp").forward(req, resp);
		}
		resp.sendRedirect("index.jsp");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.setAttribute("language", "en");

		String userLogin = req.getParameter("email");
		String userPass = req.getParameter("password");
		if(!req.getSession().isNew()){
			if(req.getSession().getAttribute("user")!=null)
				req.getSession().removeAttribute("user");
			else
				req.getSession().removeAttribute("admin");
		}

		TrainFunctions trainFunctions = trainFunctionsMain;

		List<Station> stations = trainFunctions.getAllStations();
		UsersFunctions usersFunctions = usersFunctionsMain;

		User user = usersFunctions.LoginUser(userLogin, userPass);
		Set<String> outStations = new TreeSet<>();
		for(Station st:stations){
			outStations.add(st.getLocation());
		}

		session.setAttribute("stations", stations);
		session.setAttribute("stationsName", outStations);
		AdminFunction adminFunction = adminFunctionMain;

		if (user == null) {
			Admin admin = adminFunction.LoginAdmin(userLogin, userPass);
			if (admin == null)
				res.sendRedirect("index.jsp");
			else {
				session.setAttribute("admin", admin);
				log.info("Admin " + admin.getId() + " is enter");
				req.getRequestDispatcher("/adminMain.jsp").forward(req, res);
			}
		} else {
			session.setAttribute("user", user);
			log.info("User " + user.getId() + " is enter");
			req.getRequestDispatcher("/userMain.jsp").forward(req, res);
		}

	}
}