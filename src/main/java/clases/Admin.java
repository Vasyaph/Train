package clases;

public class Admin {
	long id;
	String login;
	String password;
	String name;
	String surname;
	String email;
	String address;

	public Admin() {
	}

	public Admin(AdminBuilder adminBuilder) {
		id = adminBuilder.id;
		login = adminBuilder.login;
		password = adminBuilder.password;
		name = adminBuilder.name;
		surname = adminBuilder.surname;
		email = adminBuilder.email;
		address = adminBuilder.adres;
	}

	public static class AdminBuilder {
		long id;
		String login;
		String password;
		String name;
		String surname;
		String email;
		String adres;

		public AdminBuilder() {
		}

		public AdminBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public AdminBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public AdminBuilder setLogin(String login) {
			this.login = login;
			return this;
		}

		public AdminBuilder setEmail(String email) {
			this.email = email;
			return this;
		}


		public AdminBuilder setAddress(String address) {
			this.adres = address;
			return this;
		}

		public AdminBuilder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public AdminBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public Admin build() {
			return new Admin(this);
		}

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
}
