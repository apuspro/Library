package mpp.aed.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Book implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1102079640455412470L;
	private int copyNumber=1;
    private String title;
    private int ISBN;
    private int maxCheckoutDays;
    private final List<CopyBook> copies = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();

    public Book() {
        addCopyBook();
    }

    public Book(String title, int ISBN, int maxCheckoutDays) {
        this.title = title;
        this.ISBN = ISBN;
        this.maxCheckoutDays = maxCheckoutDays;
        addCopyBook();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getMaxCheckoutDays() {
        return maxCheckoutDays;
    }

    public void setMaxCheckoutDays(int maxCheckoutDays) {
        this.maxCheckoutDays = maxCheckoutDays;
    }

    public CopyBook getCopyBook() {
        return getAvailableCopy();
    }

    public boolean isAvailable(int ISBN) {
        if (this.ISBN == ISBN) {
            return getAvailableCopy()!=null;
        }

        return false;
    }

    public boolean addAuthor(Author author) {
        if (!authors.contains(author)) {
            return this.authors.add(author);
        }

        return false;
    }

    
    public boolean addCopyBook() {
        return this.copies.add(new CopyBook(this, copyNumber++));
    }
    
    private CopyBook getAvailableCopy() {
        for (CopyBook copy : copies) {
            if (copy.isAvailable()) {
                return copy;
            }
        }
        
        return null;
    }
    
    public int getNumberOfCopies(){
    	return this.copies.size();
    }

	public List<Author> getAuthors() {
		return authors;
	}
	
	@Override
	public boolean equals(Object object){
		if(object != null){
			if(((Book)object).ISBN==this.ISBN){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		return ISBN+" "+title+" CheckOutLenght:"+maxCheckoutDays;
	}
}
