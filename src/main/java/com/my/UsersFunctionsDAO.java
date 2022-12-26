package com.my;

import clases.Ticket;
import clases.User;

public interface UsersFunctionsDAO {
	public boolean addUser(User user);

	public User getUserById(long a);

	public User LoginUser(String login, String password);

	public Ticket getTicketById(long id);
}
