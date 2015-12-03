package mpp.aed.library;

public class SuperUser extends User {

        private final Librarian librarian;
        private final Administrator administrator;
	/**
	 * 
	 */
	private static final long serialVersionUID = -9092210971360551986L;

	public SuperUser(String username, String password) {
		super(username, password);
                librarian = new Librarian(username, password);
                administrator = new Administrator(username, password);
	}

    public Librarian getLibrarian() {
        return librarian;
    }

    public Administrator getAdministrator() {
        return administrator;
    }
    
    @Override
	public String toString(){
		return "Super User ->"+this.getUsername()+" "+this.getPassword();
	}
}
