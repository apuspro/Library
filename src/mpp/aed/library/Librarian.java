package mpp.aed.library;

import java.util.List;

public class Librarian extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3690263321000776708L;

	public Librarian(String username, String password) {
		super(username, password);
	}
        
        public List<Book> getCheckedoutBooks(int memberId) {
            return null;
        }
        
        

}
