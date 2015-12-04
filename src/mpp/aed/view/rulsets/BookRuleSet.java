package mpp.aed.view.rulsets;

import java.util.List;

import mpp.aed.library.Author;
import mpp.aed.library.Book;
import mpp.aed.library.SystemController;
import mpp.aed.view.controller.BookController;

final public class BookRuleSet implements RuleSet {
	//package level access
	BookRuleSet() {}
	@Override
	public void applyRules(Object ob) throws RuleException {
		BookController bookController = (BookController)ob;
		
		ISBNRule(bookController);
		existRule(bookController);
		titleRule(bookController);
		maxChkOutRule(bookController);
		noCopiesRule(bookController);
		authorRule(bookController);
		
	}
	
	public void applyRulesSearch(Object ob) throws RuleException {
		BookController bookController = (BookController)ob;
		ISBNRuleSearch(bookController);
	}
	
	public void applyRulesSave(Object ob) throws RuleException {
		BookController bookController = (BookController)ob;
		titleRule(bookController);
		maxChkOutRule(bookController);
		authorRule(bookController);
	}
	
	private void ISBNRule(BookController book) throws RuleException {
		String aISBN = book.getISBNField().getText();
		if(aISBN == null || aISBN.equals("")){
			throw new RuleException("ISBN must be non empty");
		}else{
			try {
				Long.parseLong(aISBN);
			} catch (NumberFormatException e) {
				throw new RuleException("ISBN must be numeric");
			}
			if(aISBN.length()!=10){
				throw new RuleException("ISBN should have a lenght of 10 digits");
			}
		}
	}
	
	private void ISBNRuleSearch(BookController book) throws RuleException {
		String aISBN = book.getISBNField().getText();
		if(aISBN == null || aISBN.equals("")){
			throw new RuleException("ISBN must be non empty");
		}
		try {
			Long.parseLong(aISBN);
		} catch (NumberFormatException e) {
			throw new RuleException("ISBN must be numeric");
		}
	}
	
	private void titleRule(BookController book) throws RuleException {
		String aTitle = book.getTitleField().getText();
		if(aTitle == null || aTitle.equals("")){
			throw new RuleException("Title must be non empty");
		}
	}
	
	private void existRule(BookController book) throws RuleException {
		String aISBN = book.getISBNField().getText();
		Book tempBook = new Book(Long.parseLong(aISBN));
		if(SystemController.getInstance().getLibrary().getBooks().contains(tempBook)) {
			throw new RuleException("Book already exists");
		}
	}
	
	private void maxChkOutRule(BookController book) throws RuleException {
		String maxChkOut = book.getMaxChecOutLengthField().getValue();
		if(maxChkOut == null || maxChkOut.contains("-")){
			throw new RuleException("Max checkout length must be non empty");
		}
	}
	
	private void noCopiesRule(BookController book) throws RuleException {
		String aNcopies = book.getNumberOfCopiesField().getText();
		try {
			Integer.parseInt(aNcopies);
		} catch (NumberFormatException e) {
			throw new RuleException("Number of copies must be numeric");
		}
		if(aNcopies.length()>9){
			throw new RuleException("Maximum number of copies exceeded");
		}
		
	}
	
	private void authorRule(BookController book) throws RuleException {
		List<Author> listAuthor = book.getNewBook().getAuthors();
		if(listAuthor == null || listAuthor.isEmpty()){
			throw new RuleException("A book must have at least one author");
		}
	}

}
