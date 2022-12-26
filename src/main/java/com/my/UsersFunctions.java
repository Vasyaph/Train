package com.my;

import DBF.TicketF;
import DBF.UsersF;
import DBF.UsersInfoF;
import clases.Ticket;
import clases.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UsersFunctions implements UsersFunctionsDAO {
	private static final Logger log = LogManager.getLogger(Login.class);

	public boolean addUser(User user) {

		Connection con = null;
		try {
			con = MyConnections.getInstance().getDs().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO users(" + UsersF.USERS_LOGIN + "," + UsersF.USERS_PASSWORD + "," + UsersF.USERS_CODE + "," + UsersF.USERS_VERIFY + ") Values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getCode());
			ps.setBoolean(4, user.getVerify());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
			PreparedStatement ps2 = con.prepareStatement("insert into users_info(" + UsersInfoF.USERS_ID + "," + UsersInfoF.USER_NAME + "," + UsersInfoF.USER_SURNAME + "," + UsersInfoF.USER_EMAIL + "," + UsersInfoF.USER_BANK_CARD + ") Values(?,?,?,?,?)");
			ps2.setLong(1, user.getId());
			ps2.setString(2, user.getName());
			ps2.setString(3, user.getSurname());
			ps2.setString(4, user.getEmail());
			ps2.setLong(5, user.getBank_card());

			ps2.executeUpdate();

			Mailer.send(user.getEmail(), "Confirm you`r email",
					"Thanks for signing up with Railwai!\n" +
					"You must follow this link to activate your account:\n" +
					"http://localhost:8080/train/verify?id="+user.getId()+"&code="+user.getCode());

			con.close();
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());

			try {
				con.close();
			} catch (SQLException ex) {
				e.printStackTrace();
			}

			return false;
		}
	}
	public void verifyUser(long a){
		Connection con = null;
		try {
			con = MyConnections.getInstance().getDs().getConnection();

			String query = "update users set " +UsersF.USERS_VERIFY +" = ? where "+UsersF.USERS_ID+" = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setBoolean   (1, true);
			preparedStmt.setLong(2,a);
			preparedStmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e.getMessage());
			try {
				con.close();

			} catch (SQLException d) {
				throw new RuntimeException(d);
			}


		}
		try {
			con.close();

		} catch (SQLException d) {
			throw new RuntimeException(d);
		}
	}

	public User getUserById(long a) {

		Connection con = null;

		User user = new User();
		try {
			con = MyConnections.getInstance().getDs().getConnection();


			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users join users_info on users." + UsersF.USERS_ID + " = users_info." + UsersInfoF.USERS_ID + " where " + UsersF.USERS_ID + "= '" + a + "'");
			if (rs.next()) {
				user.setCode(rs.getString(UsersF.USERS_CODE));
				user.setVerify(rs.getBoolean(UsersF.USERS_VERIFY));
				user.setId(rs.getLong(UsersF.USERS_ID));
				user.setPassword(rs.getString(UsersF.USERS_PASSWORD));
				user.setLogin(rs.getString(UsersF.USERS_LOGIN));
				user.setEmail(rs.getString(UsersInfoF.USER_EMAIL));
				user.setAddress(rs.getString(UsersInfoF.USER_ADRES));
				user.setName(rs.getString(UsersInfoF.USER_NAME));
				user.setSurname(rs.getString(UsersInfoF.USER_SURNAME));
				user.setBank_card(rs.getLong(UsersInfoF.USER_BANK_CARD));
				searchTicketByUser(user);
			}
			rs.close();


		} catch (SQLException e) {

			log.error(e.getMessage());
			return null;
		}
		try {
			con.close();

		} catch (SQLException d) {
			throw new RuntimeException(d);
		}

		return user;
	}

	public User LoginUser(String login, String password) {
		Connection con = null;
		User user = new User();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from users join users_info on users." + UsersF.USERS_ID + " = users_info." + UsersInfoF.USERS_ID + " where " + UsersF.USERS_LOGIN + " = '" + login + "' and " + UsersF.USERS_PASSWORD + " = '" + password + "' and "+UsersF.USERS_VERIFY + "= true");
			if (rs.next()) {

				user.setId(rs.getLong(UsersF.USERS_ID));
				user.setPassword(rs.getString(UsersF.USERS_PASSWORD));
				user.setLogin(rs.getString(UsersF.USERS_LOGIN));
				user.setEmail(rs.getString(UsersInfoF.USER_EMAIL));
				user.setAddress(rs.getString(UsersInfoF.USER_ADRES));
				user.setName(rs.getString(UsersInfoF.USER_NAME));
				user.setSurname(rs.getString(UsersInfoF.USER_SURNAME));
				user.setBank_card(rs.getLong(UsersInfoF.USER_BANK_CARD));
				searchTicketByUser(user);
			} else {
				return null;
			}
			rs.close();


		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;

	}

	public void searchTicketByUser(User user) {
		Connection con = null;
		user.setTickets(new ArrayList<>());

		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ticket_list Where " + TicketF.PERSON_ID + "='" + user.getId() + "'");
			while (rs.next()) {
				Ticket ticket = new Ticket();
				ticket.setId(rs.getLong(TicketF.ID));
				ticket.setName(rs.getString(TicketF.NAME));
				ticket.setSurname(rs.getString(TicketF.SURNAME));
				ticket.setPlace(rs.getInt(TicketF.PLACE));
				ticket.setPersonId(rs.getLong(TicketF.PERSON_ID));
				ticket.setCarriageId(rs.getLong(TicketF.CARRIAGE_ID));
				user.AddTickets(ticket);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		try {
			con.close();

		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	public Ticket getTicketById(long id) {
		Connection con = null;

		Ticket ticket = new Ticket();
		try {
			con = MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ticket_list Where " + TicketF.ID + "='" + id + "'");
			if (rs.next()) {
				ticket.setId(rs.getLong(TicketF.ID));
				ticket.setName(rs.getString(TicketF.NAME));
				ticket.setSurname(rs.getString(TicketF.SURNAME));
				ticket.setPlace(rs.getInt(TicketF.PLACE));
				ticket.setPersonId(rs.getLong(TicketF.PERSON_ID));
				ticket.setCarriageId(rs.getLong(TicketF.CARRIAGE_ID));
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}

		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return ticket;
	}


}
