package mpp.aed.library;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mpp.aed.library.dataaccess.DataAccessFacade;

public class Library implements Serializable {

    private static final long serialVersionUID = 2799993270333858879L;
    
    private static Library instance;  

    private String name;
    private List<User> users;
    private List<Member> members;
    private List<Book> books;
    
    public synchronized static Library getInstance() {
        if( instance == null ) {
            Library aLibrary = new DataAccessFacade().readLibrary("library");
            if(aLibrary!=null) {
            	instance = aLibrary;
            } else {
            	instance = new Library("Little Library");
            	User defaultUser = new SuperUser("admin", "admin2246");
            	instance.addUser(defaultUser);
            }
        }
        
        return instance;
    }

    private Library(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public String getName() {
        return name;
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean login(String username, String password) {
        User user = getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void addMember(Member newMember) throws LibraryException {
        if (members.contains(newMember)) {
            throw new LibraryException("Already exists a member with member id : " + newMember.getMemberId());
        }

        this.members.add(newMember);
    }

    public Member getMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }

        return null;
    }
    
    public boolean addBook(Book newBook) {
        if( !books.contains(newBook) ) {
            return books.add(newBook);
        }
        
        return false;
    }
    
    public Book getBookByISBN(int ISBN) {
        for( Book book : books ) {
            if( book.getISBN() == ISBN ) {
                return book;
            }
        }
        
        return null;
    }
    
    public List<Book> getBooks() {
        return this.books;
    }
}
