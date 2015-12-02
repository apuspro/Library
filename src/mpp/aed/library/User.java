package mpp.aed.library;

import java.io.Serializable;

public abstract class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6354041299431385349L;
	private String username;
	private String password;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
