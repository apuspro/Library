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
		authorRule(bookController);
		
	}
	
	private void ISBNRule(BookController book) throws RuleException {
		String aISBN = book.getISBNField().getText();
		if(aISBN == null || aISBN.equals("")){
			throw new RuleException("ISBN must be non empty");
		}else{
			if(aISBN.length()==10){
				throw new RuleException("ISBN should have at lenght of 10 characters");
			}
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
		for (Book aBook : SystemController.getInstance().getLibrary().getBooks()) {
			if(aBook.getISBN()==Integer.parseInt(aISBN)){
				throw new RuleException("Book already exists");
			}
		}
	}
	
	private void maxChkOutRule(BookController book) throws RuleException {
		String maxChkOut = book.getMaxChecOutLengthField().getText();
		if(maxChkOut == null || maxChkOut.equals("")){
			throw new RuleException("Max checkout length must be non empty");
		}
	}
	
	private void authorRule(BookController book) throws RuleException {
		List<Author> listAuthor = book.getNewBook().getAuthors();
		if(listAuthor == null || listAuthor.isEmpty()){
			throw new RuleException("A book must have at least one author");
		}
	}

}