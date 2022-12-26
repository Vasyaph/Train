package tests;

import clases.*;
import com.my.*;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DBOutTest {
	private final User user = new User.UserBuilder().setId(1).setSurname("Yehorov").setName("Vlad").setPassword("qwerty123").setEmail("Email@gmail.com").setLogin("firstUser").setCode("12345").build();
	private final Admin admin = new Admin.AdminBuilder().setId(1).setSurname("Main").setName("Admin").setPassword("qwerty123").setEmail("Email@gmail.com").setLogin("firstAdmin").build();
	private final List<Station> stations = List.of(
			new Station.StationBuilder().setId(1).setName("Kyiv-main").setCountpl(9).setLocation("Kyiv").setAddress("pochtova 26").build(),
			new Station.StationBuilder().setId(2).setName("Krivy-Rih-main").setCountpl(9).setLocation("Krivy-Rih").setAddress("misceva 26").build()
	);
	private final List<Carriage> carriages = List.of(
			new Carriage.CarriageBuilder().setId(1).setName("sit").setPrice(200).setInfo("hahahah").setNumber(1).setNumber_of_seats(20).setSpecial(false).setType_id(1).build(),
			new Carriage.CarriageBuilder().setId(2).setName("sit").setPrice(300).setInfo("hahahah").setNumber(2).setNumber_of_seats(20).setSpecial(false).setType_id(1).build(),
			new Carriage.CarriageBuilder().setId(3).setName("bar").setPrice(0).setInfo("bar").setNumber(3).setNumber_of_seats(0).setSpecial(true).setType_id(1).build()
	);
	private final Path path = new Path.PathBuilder().setId(1).setArrival(new Station()).setArrival_number(1).setArrival_time(new Timestamp(10000)).setCarriages(carriages).setDispatch_number(1).setDispatch_time(new Timestamp(1000)).setDispatch(new Station()).setTrain(new Train()).build();
	private final List<Path> paths =List.of(
			new Path.PathBuilder().setId(1).setArrival(new Station()).setArrival_number(1).setArrival_time(new Timestamp(10000)).setCarriages(carriages).setDispatch_number(1).setDispatch_time(new Timestamp(1000)).setDispatch(new Station()).setTrain(new Train()).build(),
			new Path.PathBuilder().setId(2).setArrival(new Station()).setArrival_number(2).setArrival_time(new Timestamp(20000)).setCarriages(carriages).setDispatch_number(2).setDispatch_time(new Timestamp(2000)).setDispatch(new Station()).setTrain(new Train()).build()
	);
	private Ticket ticket = new Ticket.TicketBuilder().setId(1l).setCarriageId(1).setName("Vlad").setPersonId(1).setSurname("Yehorov").setPlace(11).build();


	@Test
	void LoginUser() throws IOException, ServletException {


		Login login = new Login();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		UsersFunctions usersFunctions = mock(UsersFunctions.class);
		AdminFunction adminFunction = mock(AdminFunction.class);


		when(trainFunctions.getAllStations()).thenReturn(stations);
		login.trainFunctionsMain = trainFunctions;

		when(usersFunctions.LoginUser("firstUser", "qwerty123")).thenReturn(user);
		login.usersFunctionsMain = usersFunctions;

		login.adminFunctionMain = adminFunction;

		when(session.getAttribute("user")).thenReturn(user);
		when(request.getParameter("email")).thenReturn("firstUser");
		when(request.getParameter("password")).thenReturn("qwerty123");

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getRequestDispatcher("/userMain.jsp")).thenReturn(requestDispatcher);

		login.doPost(request, response);

		verify(request).getParameter("email");
		verify(request).getParameter("password");
		verify(trainFunctions).getAllStations();
		verify(usersFunctions).LoginUser("firstUser", "qwerty123");
		verify(request).getRequestDispatcher("/userMain.jsp");
		verify(requestDispatcher).forward(request, response);

	}

	@Test
	void loginAdmin() throws IOException, ServletException {
		Login login = new Login();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		UsersFunctions usersFunctions = mock(UsersFunctions.class);
		AdminFunction adminFunction = mock(AdminFunction.class);

		List<Station> stations = new ArrayList<>();
		stations.add(new Station());
		stations.add(new Station());
		stations.get(0).setId(1);
		stations.get(0).setName("Kyiv-main");
		stations.get(0).setCountpl(9);
		stations.get(0).setLocation("Kyiv");
		stations.get(0).setAddress("pochtova 26");
		stations.get(1).setId(2);
		stations.get(1).setName("Krivy-Rih-main");
		stations.get(1).setCountpl(9);
		stations.get(1).setLocation("Krivy-Rih");
		stations.get(1).setAddress("misceva 26");

		when(trainFunctions.getAllStations()).thenReturn(stations);
		login.trainFunctionsMain = trainFunctions;

		when(usersFunctions.LoginUser("firstUser", "qwerty123")).thenReturn(null);
		login.usersFunctionsMain = usersFunctions;

		when(adminFunction.LoginAdmin("firstAdmin", "qwerty123")).thenReturn(admin);
		login.adminFunctionMain = adminFunction;

		when(session.getAttribute("user")).thenReturn(user);
		when(request.getParameter("email")).thenReturn("firstAdmin");
		when(request.getParameter("password")).thenReturn("qwerty123");

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getRequestDispatcher("/userMain.jsp")).thenReturn(requestDispatcher);
		when(request.getRequestDispatcher("/adminMain.jsp")).thenReturn(requestDispatcher);

		login.doPost(request, response);

		verify(request).getParameter("email");
		verify(request).getParameter("password");
		verify(trainFunctions).getAllStations();
		verify(adminFunction).LoginAdmin("firstAdmin", "qwerty123");
		verify(request).getRequestDispatcher("/adminMain.jsp");
		verify(requestDispatcher).forward(request, response);
	}

	@Test
	void Registr() throws IOException, ServletException {
		Registr registr = new Registr();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		UsersFunctions usersFunctions = mock(UsersFunctions.class);
		registr.usersFunctionsMain = usersFunctions;


		when(usersFunctions.addUser(user)).thenReturn(true);
		when(request.getParameter("email")).thenReturn("Vlad@gmail.com");
		when(request.getParameter("password")).thenReturn("qwerty123");
		when(request.getParameter("lustname")).thenReturn("Yehorov");
		when(request.getParameter("card")).thenReturn("1234567890123456");
		when(request.getParameter("login")).thenReturn("Vlad");
		when(request.getParameter("name")).thenReturn("Vlad");

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getRequestDispatcher("registration.jsp")).thenReturn(requestDispatcher);

		registr.doPost(request, response);
		verify(request).getParameter("email");
		verify(request).getParameter("password");
		verify(request).getParameter("lastname");
		verify(request).getParameter("card");
		verify(request).getParameter("login");
		verify(request).getParameter("name");
		verify(request).getRequestDispatcher("registration.jsp");

	}

	@Test
	void BuyTicketTest() throws IOException, ServletException {
		BuyTicket buyTicket = new BuyTicket();
		Path pathT = new Path.PathBuilder().setReturnPatch(2).build();
		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		buyTicket.trainFunctionsMain = trainFunctions;


		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("id")).thenReturn("1");
		when(trainFunctions.getPathById(1)).thenReturn(pathT);
		when(session.getAttribute("user")).thenReturn(user);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getRequestDispatcher("/buyTicket.jsp")).thenReturn(requestDispatcher);

		buyTicket.doGet(request, response);

		verify(request).getParameter("id");
		verify(request).setAttribute("paths", pathT);
		verify(session).getAttribute("user");
		verify(request).getRequestDispatcher("/buyTicket.jsp");

	}

	@Test
	void BuyTicketAdminTest() throws IOException, ServletException {
		BuyTicket buyTicket = new BuyTicket();
		Path pathT = new Path.PathBuilder().setReturnPatch(2).build();
		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		buyTicket.trainFunctionsMain = trainFunctions;


		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("id")).thenReturn("1");
		when(trainFunctions.getPathById(1)).thenReturn(pathT);
		when(session.getAttribute("admin")).thenReturn(admin);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getRequestDispatcher("/buyTicket.jsp")).thenReturn(requestDispatcher);
		when(request.getRequestDispatcher("/buyTicketA.jsp")).thenReturn(requestDispatcher);


		buyTicket.doGet(request, response);

		verify(request).getParameter("id");
		verify(request).setAttribute("paths", pathT);
		verify(session).getAttribute("user");
		verify(request).getRequestDispatcher("/buyTicketA.jsp");


	}

	@Test
	void PaymentVereficationTest() throws IOException, ServletException {
		PaymentVerification paymentVerification = new PaymentVerification();
		Path pathT = new Path.PathBuilder().setReturnPatch(2).build();
		Ticket ticket1 = mock(Ticket.class);
		paymentVerification.ticketMain = ticket1;

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getParameter("Name")).thenReturn("Vlad");
		when(request.getParameter("Lastname")).thenReturn("Yehorov");
		when(session.getAttribute("user")).thenReturn(user);
		when(session.getAttribute("carriage")).thenReturn(carriages.get(0));
		when(session.getAttribute("place")).thenReturn(11);
		when(session.getAttribute("paths")).thenReturn(pathT);
		when(request.getRequestDispatcher("/returnTicket.jsp")).thenReturn(requestDispatcher);
		when(ticket1.AddTicket()).thenReturn(true);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");

		paymentVerification.doGet(request, response);
		verify(request).getParameter("Name");
		verify(request).getParameter("Lastname");
		verify(session).getAttribute("user");
		verify(session).getAttribute("carriage");
		verify(session).getAttribute("place");
		verify(session).getAttribute("paths");
		verify(request).getRequestDispatcher("/returnTicket.jsp");


	}

	@Test
	void ByTicket2Test() throws IOException, ServletException {
		BuyTicket2 buyTicket2 = new BuyTicket2();
		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		buyTicket2.trainFunctionsMain = trainFunctions;
		Path path = mock(Path.class);
		Carriage carriage = mock(Carriage.class);
		List<Carriage> carriageT = List.of(carriage);

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");

		when(carriage.getNumber_of_seats()).thenReturn(20);
		for (int i = 0; i < carriage.getNumber_of_seats(); i++)
			when(carriage.isFree(i + 1)).thenReturn(true);
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("carriage")).thenReturn("1");
		when(trainFunctions.getPathById(1)).thenReturn(path);
		when(path.getCarriages()).thenReturn(carriageT);
		when(request.getRequestDispatcher("/buyTicket2.jsp")).thenReturn(requestDispatcher);
		when(request.getRequestDispatcher("/buyTicket2A.jsp")).thenReturn(requestDispatcher);
		when(session.getAttribute("user")).thenReturn(user);


		buyTicket2.doGet(request, response);
		verify(request).getParameter("id");
		verify(request).getParameter("carriage");
		verify(request).getRequestDispatcher("/buyTicket2.jsp");

	}

	@Test
	void ByTicket2AdminTest() throws IOException, ServletException {
		BuyTicket2 buyTicket2 = new BuyTicket2();
		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		buyTicket2.trainFunctionsMain = trainFunctions;
		Path path = mock(Path.class);
		Carriage carriage = mock(Carriage.class);
		List<Carriage> carriageT = List.of(carriage);

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");

		when(carriage.getNumber_of_seats()).thenReturn(20);
		for (int i = 0; i < carriage.getNumber_of_seats(); i++)
			when(carriage.isFree(i + 1)).thenReturn(true);
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("carriage")).thenReturn("1");
		when(trainFunctions.getPathById(1)).thenReturn(path);
		when(path.getCarriages()).thenReturn(carriageT);
		when(request.getRequestDispatcher("/buyTicket2.jsp")).thenReturn(requestDispatcher);
		when(request.getRequestDispatcher("/buyTicket2A.jsp")).thenReturn(requestDispatcher);
		when(session.getAttribute("admin")).thenReturn(admin);


		buyTicket2.doGet(request, response);
		verify(request).getParameter("id");
		verify(request).getParameter("carriage");
		verify(request).getRequestDispatcher("/buyTicket2A.jsp");

	}

	@Test
	void ChangeLanguage() throws IOException, ServletException {
		ChangeLanguage changeLanguage = new ChangeLanguage();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");

		when(request.getParameter("lang")).thenReturn("ukr");
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getRequestDispatcher("/userProf.jsp")).thenReturn(requestDispatcher);

		changeLanguage.doGet(request, response);

		verify(request).getParameter("lang");
		verify(session).getAttribute("user");
		verify(session).setAttribute("language", "ukr");

	}

	@Test
	void IndexSTest() throws IOException, ServletException {
		IndexS indexS = new IndexS();

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");

		indexS.doGet(request, response);
		verify(session).invalidate();
		verify(response).sendRedirect("index.jsp");
	}

	@Test
	void PrepareTrainTest() throws IOException, ServletException {
		PrepareTrain prepareTrain = new PrepareTrain();
		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		Station station = mock(Station.class);
		prepareTrain.trainFunctionsMain = trainFunctions;
		Train train = mock(Train.class);

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getContextPath()).thenReturn("localhost:8080");
		when(request.getParameter("TrainId")).thenReturn("1");
		when(request.getParameter("Departure")).thenReturn("Kyiv");
		when(request.getParameter("Arrival")).thenReturn("Lviv");
		when(request.getParameter("DepartureT")).thenReturn("2022-10-03 10:09");
		when(request.getParameter("ArrivalT")).thenReturn("2022-10-04 10:09");
		when(request.getParameter("ArrivalNumber")).thenReturn("1");
		when(request.getParameter("DepartureNumber")).thenReturn("1");
		when(request.getParameter("numberW")).thenReturn("5");
		when(trainFunctions.getTrainById(1)).thenReturn(train);
		when(trainFunctions.getStationByName("Kyiv")).thenReturn(station);
		when(trainFunctions.getStationByName("Lviv")).thenReturn(station);
		when(request.getRequestDispatcher("/addCarriage.jsp")).thenReturn(requestDispatcher);

		prepareTrain.doGet(request, response);
		verify(request).getParameter("TrainId");
		verify(request).getParameter("Departure");
		verify(request).getParameter("DepartureT");
		verify(request).getParameter("ArrivalT");
		verify(request).getParameter("ArrivalNumber");
		verify(request).getParameter("DepartureNumber");
		verify(request).getParameter("numberW");


	}

	@Test
	void TicketInfoTest() throws IOException, ServletException {
		TicketInfo ticketInfo = new TicketInfo();
		UsersFunctions usersFunctions = mock(UsersFunctions.class);
		ticketInfo.usersFunctionsMain = usersFunctions;

		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		ticketInfo.trainFunctionsMain = trainFunctions;

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);


		when(request.getSession()).thenReturn(session);
		when(request.getParameter("ticketId")).thenReturn("1");
		when(session.getAttribute("user")).thenReturn(user);
		when(usersFunctions.getTicketById(1)).thenReturn(ticket);
		when(trainFunctions.getCarriagesById(1)).thenReturn(carriages.get(0));
		when(trainFunctions.getPathByCarriageId(1)).thenReturn(path);
		when(request.getRequestDispatcher("/ticketInfo.jsp")).thenReturn(requestDispatcher);

		ticketInfo.doGet(request, response);

		verify(request).getSession();
		verify(request).getParameter("ticketId");
		verify(session).getAttribute("user");
		verify(usersFunctions).getTicketById(1);
		verify(trainFunctions).getCarriagesById(1);
		verify(trainFunctions).getPathByCarriageId(1);
		verify(request).getRequestDispatcher("/ticketInfo.jsp");
	}

	@Test
	void VerifyTest() throws IOException, ServletException {
		Verify verify1 = new Verify();
		UsersFunctions usersFunctions = mock(UsersFunctions.class);
		verify1.usersFunctionsMain = usersFunctions;


		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("code")).thenReturn("12345");
		when(usersFunctions.getUserById(1)).thenReturn(user);
		when(request.getRequestDispatcher("/message.jsp")).thenReturn(requestDispatcher);

		verify1.doGet(request, response);

		verify(request).getParameter("id");
		verify(request).getParameter("code");
		verify(usersFunctions).getUserById(1);
		verify(request).getRequestDispatcher("/message.jsp");


	}

	@Test
	void UserMainTest() throws IOException, ServletException {
		UserMain userMain = new UserMain();

		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		userMain.trainFunctionsMain = trainFunctions;

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getParameter("counter")).thenReturn("0");
		when(session.getAttribute("allPaths")).thenReturn(paths);

		userMain.doGet(request,response);
		verify(request).getParameter("counter");
		verify(session).getAttribute("allPaths");

	}
	@Test
	void UserMainTest2() throws IOException, ServletException {
		UserMain userMain = new UserMain();

		TrainFunctions trainFunctions = mock(TrainFunctions.class);
		userMain.trainFunctionsMain = trainFunctions;

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getParameter("counter")).thenReturn(null);
		when(session.getAttribute("allPaths")).thenReturn(paths);
		when(trainFunctions.GetAllPath()).thenReturn(paths);
		when(request.getParameter("departure")).thenReturn("Kyiv");
		when(request.getParameter("arrival")).thenReturn("Kharkiv");
		when(request.getParameter("departureT")).thenReturn("1997-03-10");
		when(session.getAttribute("user")).thenReturn(user);
		when(request.getRequestDispatcher("/userMain.jsp")).thenReturn(requestDispatcher);


		userMain.doGet(request,response);

		verify(request).getParameter("counter");
		verify(trainFunctions).GetAllPath();
		verify(request).getParameter("departure");
		verify(request).getParameter("arrival");
		verify(request).getParameter("departureT");
		verify(session).getAttribute("user");
		verify(request).getRequestDispatcher("/userMain.jsp");



	}


}
