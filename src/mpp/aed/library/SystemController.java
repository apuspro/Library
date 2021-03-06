package mpp.aed.library;

import mpp.aed.library.dataaccess.DataAccessFacade;

public class SystemController {

    private Library library;
    private User currentUser;
    
    private static SystemController instance = new SystemController();

    public SystemController() {
        //First we are going to try to deserialize the Library
        this.library = Library.getInstance();
        //serialize to create the first object for the project
        serialize(this.library);
    }
    
    public static SystemController getInstance(){
    	return instance;
    }

    public User getUser(String username){
    	return this.library.getUser(username);
    }
    
    public boolean login(String username, String password) {
    	User tempUser = this.library.login(username, password);
    	if(tempUser != null){
    		this.setCurrentUser(tempUser);
    		return true;
    	}
        return false;
    }

    /**
     * Method to deserialize the Library
     *
     * @param libraryName
     * @return
     */
    public Library desearialize(String libraryName) {
        DataAccessFacade dataAccess = new DataAccessFacade();
        return dataAccess.readLibrary(libraryName);
    }

    /**
     * Method that serialize the Library
     *
     * @param library
     */
    public void serialize(Library library) {
        DataAccessFacade dataAccess = new DataAccessFacade();
        dataAccess.saveLibrary("library", library);;
    }

    /**
     * @return the library
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * @param library the library to set
     */
    public void setLibrary(Library library) {
        this.library = library;
    }
    

    public void createMember(int memberId, String firstName, String lastName, String street, String city, String state, String zip,
            long phoneNumber) throws LibraryException {
        Member newMember = new Member(memberId, firstName, lastName, street, city, state, zip, phoneNumber);
        this.library.addMember(newMember);
        this.serialize(this.library);
    }
    
    public void addCopyToBook(Book book){
    	if(book.addCopyBook())
    		this.serialize(library);
    }

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public boolean addBook(Book book){
		if(book!=null){
			return this.library.addBook(book);
		}
		return false;
	}
	
	public boolean addUser(String username, String password, String type){
		if(username!=null && password!=null && type!=null){
			User newUser;
			if(type.equals("Administrator")){
				newUser = new Administrator(username, password);
			}else if(type.equals("Librarian")){
				newUser = new Librarian(username, password);
			}else{
				newUser = new SuperUser(username, password);
			}
			
			return this.library.addUser(newUser);
		}
		return false;
	}
	
	public void printUsers(){
		 this.library.printUsers();
		
	}
	
	public void printBooks(){
		this.library.printBooks();
	}
	
	public void printMembers(){
		this.library.printMembers();
	}
}
