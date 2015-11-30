package projectstartup.librarysample.business;

import java.io.Serializable;
import java.time.LocalDate;

public class LibraryMember implements Serializable {
	private CheckoutRecord record = new CheckoutRecord();
	public LibraryMember(String name) {
		this.name = name;
	}
	private String name;
	
	public void checkout(LendableCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		CheckoutRecordEntry entry = new CheckoutRecordEntry(copy, checkoutDate, dueDate);
		record.addEntry(entry);
		
	}
	
	public String toString() {
		return "Checkout record for library member " + name + ": " + record;
	}
	
	private static final long serialVersionUID = -2226197306790714013L;
}
