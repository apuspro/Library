package mpp.aed.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2799993270333858879L;
	
	private String name;
	private List<User> users;
	
	public Library(String name){
		this.name = name;
		this.users = new ArrayList<>();
	}
	
	public boolean addUser(User user){
		return users.add(user);
	}

	public String getName() {
		return name;
	}
	
	private User getUser(String username){
		for (User user : users) {
			if(user.getUsername().equals(username)) return user;
		}
		return null;
	}
	
	public boolean login(String username, String password){
		User user = getUser(username);
		if(user!=null && user.getPassword().equals(password)){
			return true;
		}
		return false;
	}
}
