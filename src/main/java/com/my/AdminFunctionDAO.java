package com.my;

import clases.Admin;

public interface AdminFunctionDAO {
	public boolean addAdmin(Admin admin);

	public Admin getAdminById(long a);

	public Admin LoginAdmin(String login, String password);


}
