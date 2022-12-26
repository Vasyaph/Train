package clases;

import DBF.TicketF;
import com.my.MyConnections;

import java.sql.*;

public class Carriage {
	long id;
	long type_id;
	String name;
	int number_of_seats;
	boolean special;
	String info;
	int number;
	int price;

	public Carriage() {

	}

	public Carriage(CarriageBuilder carriageBuilder) {
		id = carriageBuilder.id;
		type_id = carriageBuilder.type_id;
		name = carriageBuilder.name;
		number_of_seats = carriageBuilder.number_of_seats;
		special = carriageBuilder.special;
		info = carriageBuilder.info;
		number = carriageBuilder.number;
		price = carriageBuilder.price;
	}

	public static class CarriageBuilder {
		long id;
		long type_id;
		String name;
		int number_of_seats;
		boolean special;
		String info;
		int number;
		int price;

		public CarriageBuilder() {

		}


		public CarriageBuilder setNumber_of_seats(int number_of_seats) {
			this.number_of_seats = number_of_seats;
			return this;
		}

		public CarriageBuilder setInfo(String info) {
			this.info = info;
			return this;
		}

		public CarriageBuilder setSpecial(boolean special) {
			this.special = special;
			return this;
		}

		public CarriageBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public CarriageBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CarriageBuilder setType_id(long type_id) {
			this.type_id = type_id;
			return this;
		}

		public CarriageBuilder setNumber(int number) {
			this.number = number;
			return this;
		}

		public CarriageBuilder setPrice(int price) {
			this.price = price;
			return this;
		}

		public Carriage build() {
			return new Carriage(this);
		}

	}

	public long getType_id() {
		return type_id;
	}

	public void setType_id(long type_id) {
		this.type_id = type_id;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public boolean isSpecial() {
		return special;
	}

	public long getId() {
		return id;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public int getPrice() {
		return price;
	}

	public String getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public void setTypeParam(CarriageType ct) {
		setType_id(ct.getId());
		setNumber_of_seats(ct.getNumber_of_seats());
		setInfo(ct.getInfo());
		setName(ct.getName());
		setSpecial(ct.isSpecial());
	}

	public boolean isFree(int number) {
		Connection con = MyConnections.getConnection();
		boolean out = number <= number_of_seats;

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from ticket_list where " + TicketF.CARRIAGE_ID + " = '" + id + "' and " + TicketF.PLACE + " = '" + number + "'");
			if (rs.next()) {
				out = false;
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return out;
	}

	public PlaceInfo infoByPlace(int number) {
		if (isFree(number))
			return null;
		Connection con = MyConnections.getConnection();
		PlaceInfo placeI = new PlaceInfo();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from ticket_list where " + TicketF.CARRIAGE_ID + " = '" + id + "' and " + TicketF.PLACE + " = '" + number + "'");
			if (rs.next()) {
				placeI.setPlace(rs.getInt(TicketF.PLACE));
				placeI.setCarriage_id(rs.getInt(TicketF.CARRIAGE_ID));
				placeI.setBuyer_id(rs.getLong(TicketF.PERSON_ID));
				placeI.setName(rs.getString(TicketF.NAME));
				placeI.setSurname(rs.getString(TicketF.SURNAME));
			}
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return placeI;
	}

	public int getFreePlaces() {
		int count = 0;
		for (int i = 1; i <= number_of_seats; i++) {
			if (isFree(i)) {
				count++;
			}
		}

		return count;
	}

	public Carriage(String name) {
		this.name = name;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Carriage time = (Carriage) obj;
		return time.getName().equals(this.getName());
	}
}
