package com.my;


import clases.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verify")
public class Verify extends HttpServlet {
	public UsersFunctions usersFunctionsMain = new UsersFunctions();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long incomingId=Long.parseLong(req.getParameter("id"));
		String incomingCode=req.getParameter("code");
		System.out.println(incomingId+"\n"+incomingCode);
		User user =usersFunctionsMain.getUserById(incomingId);
		if(user.getCode().equals(incomingCode)){
			usersFunctionsMain.verifyUser(incomingId);
			req.setAttribute("title", "Success");
			req.setAttribute("message", "Your account has been successfully verified");

		}else {
			req.setAttribute("title", "Error");
			req.setAttribute("message", "An error occurred while verifying the account. Please try again later or register again");
		}
		getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);

	}
}
