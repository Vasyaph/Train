package clases;

import java.util.ArrayList;
import java.util.List;

public class User {
	long id;

	String login;
	String password;
	String name;
	String surname;
	String email;
	long bank_card;
	String address;
	boolean verify;
	String code;
	List<Ticket> tickets = new ArrayList<>();

	public User() {
	}

	public User(UserBuilder userBuilder) {
		id = userBuilder.id;
		login = userBuilder.login;
		password = userBuilder.password;
		name=userBuilder.name;
		surname = userBuilder.surname;
		email = userBuilder.email;
		bank_card = userBuilder.bank_card;
		address = userBuilder.adres;
		tickets = userBuilder.tickets;
		code= userBuilder.code;
		verify=userBuilder.verify;

	}

	public static class UserBuilder {
		private long id;

		private String login;
		private String password;
		private String name;
		private String surname;
		private String email;
		private long bank_card;
		private String adres;
		private boolean verify;
		private String code;
		private List<Ticket> tickets = new ArrayList<>();

		public UserBuilder() {
		}

		public UserBuilder setCode(String code) {
			this.code = code;
			return this;
		}

		public UserBuilder setVerify(boolean verify) {
			this.verify = verify;
			return this;
		}

		public UserBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public UserBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public UserBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public UserBuilder setAdres(String adres) {
			this.adres = adres;
			return this;
		}

		public UserBuilder setBank_card(long bank_card) {
			this.bank_card = bank_card;
			return this;
		}

		public UserBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder setLogin(String login) {
			this.login = login;
			return this;
		}

		public UserBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public UserBuilder setTickets(List<Ticket> tickets) {
			this.tickets = tickets;
			return this;
		}

		public User build() {
			return new User(this);
		}

	}

	public void AddTickets(Ticket a) {
		tickets.add(a);
	}

	public void removeTicketsById(long a) {
		for (Ticket ticket :
				tickets) {
			if (ticket.getId() == a) {
				tickets.remove(ticket);
			}
		}

	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getSurname() {
		return surname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public boolean getVerify() {
		return verify;
	}

	public void setBank_card(long bank_card) {
		this.bank_card = bank_card;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public long getBank_card() {
		return bank_card;
	}

}
