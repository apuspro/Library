package mpp.aed.library;

import mpp.aed.library.dataaccess.DataAccessFacade;

public class SystemController {
	private Library library;

	public SystemController(){
		//First we are going to try to deserialize the Library
		Library aLibrary = desearialize("library");
		if(aLibrary!=null){
			this.library = aLibrary;
			System.out.println("Object Serialized Obtained");
		}else{
			this.library = new Library("Little Library");
			User defaultUser = new SuperUser("admin", "admin2246");
			this.library.addUser(defaultUser);
			//serialize to create the first object for the project
			serialize(this.library);
		}
	}
	
	public boolean login(String username, String password){
		return this.library.login(username, password);
	}
	
	/**
	 * Method to deserialize the Library
	 * @param libraryName
	 * @return
	 */
	public Library desearialize(String libraryName){
		DataAccessFacade dataAccess = new DataAccessFacade();
		return dataAccess.readLibrary(libraryName);
	}
	
	/**
	 * Method that serialize the Library
	 * @param library
	 */
	public void serialize(Library library){
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

}