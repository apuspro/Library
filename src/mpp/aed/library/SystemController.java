package mpp.aed.library;

import mpp.aed.library.dataaccess.DataAccessFacade;

public class SystemController {

    private Library library;

    public SystemController() {
        //First we are going to try to deserialize the Library
        this.library = Library.getInstance();
        User defaultUser = new SuperUser("admin", "admin2246");
        this.library.addUser(defaultUser);
        //serialize to create the first object for the project
        serialize(this.library);
    }

    public boolean login(String username, String password) {
        return this.library.login(username, password);
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
            int phoneNumber) throws LibraryException {
        Member newMember = new Member(memberId, firstName, lastName, street, city, state, zip, phoneNumber);
        this.library.addMember(newMember);
        this.serialize(this.library);
    }
}
