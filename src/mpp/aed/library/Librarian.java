package mpp.aed.library;

import java.time.LocalDate;
import java.util.List;

public class Librarian extends User {

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

    public boolean checkoutBook(int memberId, int ISDN) throws MemberException,BookException {
        Library library = Library.getInstance();
        Member member = library.getMemberById(memberId);
        if( member == null ) {
            throw new MemberException("This member is not exists in member list");
        }
        
        Book book = library.getBookByISBN(ISDN);
        if( book == null ) {
            throw new BookException("This book is not exists in this library");
        }
        
        CopyBook copyBook = book.getCopyBook();
        if( copyBook == null ) {
            return false;
        }
        copyBook.setAvailable(false);
        
        Entry entry = new Entry();
        entry.setCheckoutDate(LocalDate.now());
        entry.setDueDate(LocalDate.now().plusDays(book.getMaxCheckoutDays()));
        entry.setCopyBook(copyBook);
        CheckOutRecord cor = member.getCheckOutRecord();
        cor.addEntry(entry);
        
        SystemController.getInstance().serialize(library);
        System.out.println("The book checked out!!!");        
        
        return true;
    }
}
