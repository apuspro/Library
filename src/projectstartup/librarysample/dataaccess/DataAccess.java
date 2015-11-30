package projectstartup.librarysample.dataaccess;

import projectstartup.librarysample.business.LibraryMember;

public interface DataAccess {
	public void saveLibraryMember(String name, LibraryMember member);
	public LibraryMember readLibraryMember(String name);
}
