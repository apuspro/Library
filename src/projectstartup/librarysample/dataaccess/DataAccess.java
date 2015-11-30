package projectstartup.librarysample.dataaccess;

import projectstartup.librarysample.business.Library;

public interface DataAccess {
	public void saveLibrary(String name, Library library);
	public Library readLibrary(String name);
}
