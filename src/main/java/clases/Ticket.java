package clases;

import DBF.CarriageF;
import DBF.CarriageTF;
import DBF.TicketF;
import com.my.MyConnections;

import java.sql.*;

public class Ticket {
	private Long id;
	private String name;
	private String surname;
	private int place;
	private long carriageId;
	private long personId;

	public Ticket() {
	}

	public Ticket(TicketBuilder ticketBuilder) {
		id = ticketBuilder.id;
		name = ticketBuilder.name;
		surname = ticketBuilder.surname;
		place = ticketBuilder.place;
		carriageId = ticketBuilder.carriageId;
		personId = ticketBuilder.personId;
	}

	public static class TicketBuilder {
		private Long id;
		private String name;
		private String surname;
		private int place;
		private long carriageId;
		private long personId;

		public TicketBuilder() {
		}

		public TicketBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public TicketBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public TicketBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public TicketBuilder setPlace(int place) {
			this.place = place;
			return this;
		}

		public TicketBuilder setPersonId(long personId) {
			this.personId = personId;
			return this;
		}

		public TicketBuilder setCarriageId(long carriageId) {
			this.carriageId = carriageId;
			return this;
		}

		public Ticket build() {
			return new Ticket(this);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCarriageId(long carriageId) {
		this.carriageId = carriageId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPlace() {
		return place;
	}

	public long getCarriageId() {
		return carriageId;
	}

	public long getPersonId() {
		return personId;
	}

	public String getSurname() {
		return surname;
	}

	public boolean AddTicket() {
		Connection con = MyConnections.getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from ticket_list where " + TicketF.CARRIAGE_ID + " = '" + carriageId + "' and " + TicketF.PLACE + " = '" + place + "'");
			if (rs.next()) {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select " + CarriageTF.NUMBER_OF_SEATS + " from carriage_type where id=(select " + CarriageF.TYPE + " from railway_composition where id='" + carriageId + "')");
			if (rs.next()) {
				if (place > rs.getLong(CarriageTF.NUMBER_OF_SEATS)&&place<=0)
					return false;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try (PreparedStatement ps = con.prepareStatement("INSERT INTO ticket_list(" + TicketF.PLACE + "," + TicketF.CARRIAGE_ID + "," + TicketF.PERSON_ID + "," + TicketF.NAME + "," + TicketF.SURNAME + ") Values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
			ps.setLong(1, place);
			ps.setLong(2, carriageId);
			ps.setLong(3, personId);
			ps.setString(4, name);
			ps.setString(5, surname);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				setId(rs.getLong(1));
			}


			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
