package com.my;

import DBF.*;
import clases.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AdminFunction implements AdminFunctionDAO {
	private static final Logger log = LogManager.getLogger(Login.class);
	public boolean addAdmin(Admin admin) {

		Connection con= null;
		try {
			con = MyConnections.getInstance().getDs().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try (PreparedStatement ps = con.prepareStatement("INSERT INTO admins(" + AdminsF.ADMINS_LOGIN + "," + AdminsF.ADMINS_PASSWORD + ") Values(?,?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, admin.getLogin());
			ps.setString(2, admin.getPassword());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				admin.setId(rs.getInt(1));
			}
			PreparedStatement ps2 = con.prepareStatement("insert into admins_info(" + AdminsIF.ADMINS_ID + "," + AdminsIF.ADMINS_NAME + "," + AdminsIF.ADMINS_SURNAME + "," + AdminsIF.ADMINS_EMAIL + "," + ") Values(?,?,?,?)");
			ps2.setLong(1, admin.getId());
			ps2.setString(2, admin.getName());
			ps2.setString(3, admin.getSurname());
			ps2.setString(4, admin.getEmail());
			ps2.executeUpdate();

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

	public Admin getAdminById(long a) {

		Connection con = null;

		Admin admin = new Admin();
		try {
			con= MyConnections.getInstance().getDs().getConnection();

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admins join admins_info on admin." + AdminsF.ADMINS_ID + " = admins_info." + AdminsIF.ADMINS_ID + " where " + AdminsF.ADMINS_ID + "= '" + a + "'");
			if (rs.next()) {
				admin.setId(rs.getLong(AdminsF.ADMINS_ID));
				admin.setPassword(rs.getString(AdminsF.ADMINS_PASSWORD));
				admin.setLogin(rs.getString(AdminsF.ADMINS_LOGIN));
				admin.setEmail(rs.getString(AdminsIF.ADMINS_EMAIL));
				admin.setAddress(rs.getString(AdminsIF.ADMINS_ADRES));
				admin.setName(rs.getString(AdminsIF.ADMINS_NAME));
				admin.setSurname(rs.getString(AdminsIF.ADMINS_SURNAME));
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

		return admin;
	}

	public Admin LoginAdmin(String login, String password) {
		Connection con = null;
		Admin admin = new Admin();
		try {
			con= MyConnections.getInstance().getDs().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admins join admins_info on admins." + AdminsF.ADMINS_ID + " = admins_info." + AdminsIF.ADMINS_ID + " where " + AdminsF.ADMINS_LOGIN + "= '" + login + "' and " + AdminsF.ADMINS_PASSWORD + "= '" + password + "'");
			if (rs.next()) {

				admin.setId(rs.getLong(AdminsF.ADMINS_ID));
				admin.setPassword(rs.getString(AdminsF.ADMINS_PASSWORD));
				admin.setLogin(rs.getString(AdminsF.ADMINS_LOGIN));
				admin.setEmail(rs.getString(AdminsIF.ADMINS_EMAIL));
				admin.setAddress(rs.getString(AdminsIF.ADMINS_ADRES));
				admin.setName(rs.getString(AdminsIF.ADMINS_NAME));
				admin.setSurname(rs.getString(AdminsIF.ADMINS_SURNAME));


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
		return admin;

	}


}
