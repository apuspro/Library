package mpp.aed.library;

import java.io.Serializable;

public class Member implements Serializable {
	private int memberId;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private int phoneNumber;
	private CheckOutRecord checkOutRecord = new CheckOutRecord();
	
	public Member(int memberId, String firstName, String lastName, String street, String city, String state, String zip,
			int phoneNumber) {
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}

	public CheckOutRecord getCheckOutRecord() {
		return checkOutRecord;
	}

	public void setCheckOutRecord(CheckOutRecord checkOutRecord) {
		this.checkOutRecord = checkOutRecord;
	}

	public int getMemberId() {
		return memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	@Override
	public boolean equals(Object obj){
		Member member = (Member)obj;
		return member.memberId == this.memberId;
	}
}
