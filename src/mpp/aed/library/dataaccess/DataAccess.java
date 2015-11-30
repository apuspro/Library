package mpp.aed.library.dataaccess;

import mpp.aed.library.Library;

public interface DataAccess {
	public void saveLibrary(String name, Library library);
	public Library readLibrary(String name);
}
