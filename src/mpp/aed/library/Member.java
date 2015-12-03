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
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private long phoneNumber;
	private CheckOutRecord checkOutRecord = new CheckOutRecord();
	
	public Member(int memberId, String firstName, String lastName, String street, String city, String state, String zip,
			long phoneNumber) {
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

	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	@Override
	public boolean equals(Object obj){
		Member member = (Member)obj;
		return member.memberId == this.memberId;
	}
}
