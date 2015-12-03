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
	
	@Override
	public boolean equals(Object object){
		if(object != null){
			if(((User)object).username.equals(this.username)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return username+" "+password;
	}
}
