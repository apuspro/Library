package mpp.aed.library;

import java.io.Serializable;
import java.time.LocalDate;

public class Entry implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6578228472303519129L;
	private boolean checkedout;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private CopyBook copyBook;
    
    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCheckedout() {
        return checkedout;
    }

    public void setCheckedout(boolean checkedout) {
        this.checkedout = checkedout;
    }   

    public CopyBook getCopyBook() {
        return copyBook;
    }

    public void setCopyBook(CopyBook copyBook) {
        this.copyBook = copyBook;
    }   
}
