package com.my;

import clases.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registr")
public class Registr extends HttpServlet {
	private static final Logger log = LogManager.getLogger(HttpServlet.class);
	public UsersFunctions usersFunctionsMain = new UsersFunctions();
	private boolean checkReg(String regex, String fields){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fields);
		return matcher.matches();
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Long card;


			String email = "";
			String password = "";
			String lastname = "";
			String login = "";
			String name = "";
			String cardStr="";

			//create reg. syntax
			String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
			String cardRegex = "[0-9\\s]{16}";
			//create reg. syntax

			//get fields
			email = req.getParameter("email");
			password = req.getParameter("password");
			lastname = req.getParameter("lastname");
			login = req.getParameter("login");
			name = req.getParameter("name");
			cardStr=req.getParameter("card");

			//get fields

			//check fields
			boolean check=false;

			if(!checkReg(emailRegex,email)){
				req.setAttribute("emailError", "errorF");
				check=true;
			}
			if(!checkReg(cardRegex,cardStr)){
				req.setAttribute("cardError", "errorF");
				check=true;
			}
			//check fields
			if(check){
				req.getRequestDispatcher("registration.jsp").forward(req, res);
			}


			card = Long.parseLong(cardStr);
			//randon nummber
			int min = 10000;
			int max = 99999;
			int diff = max - min;
			Random random = new Random();
			int i = random.nextInt(diff + 1);
			i += min;
			String code=Integer.toString(i);
			//random

			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setLogin(login);
			user.setPassword(password);
			user.setSurname(lastname);
			user.setBank_card(card);
			user.setCode(code);
			user.setVerify(false);

			UsersFunctions usersFunctions = usersFunctionsMain;

			if (usersFunctions.addUser(user)) {
				log.info("Create new user with id" + user.getId());
				req.setAttribute("title", "Check you`r email");
				req.setAttribute("message", "We have sent instructions to your email to continue registration. If the instruction did not come, then register again");
				req.getRequestDispatcher("message.jsp").forward(req, res);
			} else {
				req.setAttribute("error", true);
				req.getRequestDispatcher("registration.jsp").forward(req, res);
			}
		} catch (NumberFormatException e) {
			res.sendRedirect("registration.jsp");
		}


	}
}
