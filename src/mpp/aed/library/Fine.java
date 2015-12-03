package mpp.aed.library;

import java.io.Serializable;
import java.time.LocalTime;

public class Fine implements Serializable { 
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8616708022221252647L;
	private int number;
    private double amount;
    private int daysLate;
    private LocalTime dateOfReturn;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public LocalTime getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalTime dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }   
}
