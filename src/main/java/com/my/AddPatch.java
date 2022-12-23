package com.my;

import clases.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/submit")
public class AddPatch extends HttpServlet {
	private static final Logger log = LogManager.getLogger(AddPatch.class);


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {


			Path path = new Path();
			TrainFunctions trainFunctions = new TrainFunctions();


			Train train = trainFunctions.getTrainById((long) req.getSession().getAttribute("TrainId"));
			path.setTrain(train);

			path.setArrival_time((Timestamp) req.getSession().getAttribute("ArrivalT"));
			path.setDispatch_time((Timestamp) req.getSession().getAttribute("DepartureT"));
			path.setDispatch((Station) req.getSession().getAttribute("Departure"));
			path.setArrival((Station) req.getSession().getAttribute("Arrival"));
			path.setArrival_number((Integer) req.getSession().getAttribute("arrivalN"));
			path.setDispatch_number((Integer) req.getSession().getAttribute("departureN"));
			List<Carriage> carriages = new ArrayList<>();
			List<Carriage> numberW = (List<Carriage>) req.getSession().getAttribute("numberW");

			for (int i = 0; i < numberW.size(); i++) {
				carriages.add(new Carriage());
				carriages.get(i).setNumber(i + 1);
				carriages.get(i).setPrice(Integer.parseInt(req.getParameter("CarriagePrice" + (i + 1))));
				carriages.get(i).setTypeParam(trainFunctions.getCarriageTypeById(Long.parseLong(req.getParameter("Carriage" + (i + 1)))));
			}
			path.setCarriages(carriages);
			try {
				long vc = (long) req.getSession().getAttribute("return");
				path.setReturnPatch(vc);
				System.out.println("patch wil be created");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("patch not created");
			}
			req.getSession().removeAttribute("return");
			req.getSession().removeAttribute("ArrivalT");
			req.getSession().removeAttribute("DepartureT");
			req.getSession().removeAttribute("Departure");
			req.getSession().removeAttribute("Arrival");
			req.getSession().removeAttribute("numberW");
			req.getSession().removeAttribute("TrainId");
			trainFunctions.addPath(path);
			log.info("Create new patch with id " + path.getId());

			getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
		}catch (NullPointerException exception){
			getServletContext().getRequestDispatcher("/adminMain.jsp").forward(req, resp);
		}


	}
}
