package mpp.aed.library;

import java.io.Serializable;

public class Author implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8376853410333439487L;
	private String firstName;
	private String lastName;
	private String address;
	private int phoneNumber;
	private String credentials;
	private String bio;
	
	public Author(String firstName, String lastName, String address, int phoneNumber, String credentials, String bio) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.credentials = credentials;
		this.bio = bio;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getCredentials() {
		return credentials;
	}

	public String getBio() {
		return bio;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null){
			if(((Author)object).firstName.equals(this.firstName) && ((Author)object).lastName.equals(this.lastName)){
				return true;
			}
		}
		return false;
	}
}
