package mpp.aed.library;

import java.io.Serializable;

public class CopyBook implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8517552735563912947L;
	private int copyNumber;
    private boolean available = true;
    private final Book original;

    public CopyBook(Book original, int copyNumber) {
        this.original = original;
        this.copyNumber = copyNumber;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }   

    public Book getOriginal() {
        return original;
    }  
}
