package com.my;


import clases.Carriage;
import clases.CarriageType;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addTrain")
public class PrepareTrain extends HttpServlet {
	public TrainFunctions trainFunctionsMain=new TrainFunctions();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		long trainId = Long.parseLong(req.getParameter("TrainId"));
		String departure = req.getParameter("Departure");
		String arrival = req.getParameter("Arrival");
		String departureT = (req.getParameter("DepartureT"));
		String arrivalT = (req.getParameter("ArrivalT"));
		int arrivalN = Integer.parseInt((req.getParameter("ArrivalNumber")));
		int departureN = Integer.parseInt((req.getParameter("DepartureNumber")));
		List<Carriage> numberW = new ArrayList<>();
		int count = Integer.parseInt(req.getParameter("numberW"));
		for (int a = 0; a < count; a++) {
			numberW.add(new Carriage());
			numberW.get(a).setNumber(a + 1);
		}
		departureT = departureT.replace("T", " ");

		arrivalT = arrivalT.replace("T", " ");


		Timestamp dT = Timestamp.valueOf(departureT + ":00");
		Timestamp aT = Timestamp.valueOf(arrivalT + ":00");
		TrainFunctions trainFunctions = trainFunctionsMain;


		if ((trainFunctions.getTrainById(trainId) == null) || (trainFunctions.getStationByName(departure) == null) || (trainFunctions.getStationByName(arrival) == null)) {
			resp.sendRedirect("addTrain.jsp");
		} else {
			List<CarriageType> allCarriageTypes = trainFunctions.getCarriageTypeList();
			session.setAttribute("TrainId", trainId);
			session.setAttribute("Departure", trainFunctions.getStationByName(departure));
			session.setAttribute("Arrival", trainFunctions.getStationByName(arrival));
			session.setAttribute("DepartureT", dT);
			session.setAttribute("ArrivalT", aT);
			session.setAttribute("numberW", numberW);
			session.setAttribute("arrivalN", arrivalN);
			session.setAttribute("departureN", departureN);
			session.setAttribute("allCarriageTypes", allCarriageTypes);
			try {
				long retWagon = Long.parseLong(req.getParameter("return"));
				session.setAttribute("return", retWagon);
			}catch (NumberFormatException e){
				session.setAttribute("return", 0);
			}
			req.getRequestDispatcher("/addCarriage.jsp").forward(req, resp);
		}


	}
}
