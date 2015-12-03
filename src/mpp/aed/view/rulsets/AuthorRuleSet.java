package mpp.aed.view.rulsets;

import mpp.aed.library.Author;
import mpp.aed.view.controller.AuthorController;

final public class AuthorRuleSet implements RuleSet {
	//package level access
	AuthorRuleSet() {}
	@Override
	public void applyRules(Object ob) throws RuleException {
		AuthorController auhorController = (AuthorController)ob;
		
		firstNameRule(auhorController);
		LastNameRule(auhorController);
		addressRule(auhorController);
		phoneRule(auhorController);
		credentialsRule(auhorController);
		bioRule(auhorController);
		existsRule(auhorController);
		
	}
	
	private void firstNameRule(AuthorController author) throws RuleException {
		String firstName = author.getFirstNameField().getText();
		if(firstName == null || firstName.equals("")){
			throw new RuleException("FirstName must be non empty");
		}
	}
	
	private void LastNameRule(AuthorController author) throws RuleException {
		String lastName = author.getLastNameField().getText();
		if(lastName == null || lastName.equals("")){
			throw new RuleException("LastName must be non empty");
		}
	}
	
	private void addressRule(AuthorController author) throws RuleException {
		String address = author.getAddressField().getText();
		if(address == null || address.equals("")){
			throw new RuleException("Address must be non empty");
		}
	}

	private void phoneRule(AuthorController author) throws RuleException {
		String phone = author.getPhoneNumberField().getText();
		if(phone == null || phone.equals("")){
			throw new RuleException("Phone must be non empty");
		}
		try {
			Long.parseLong(phone);
		} catch (NumberFormatException e) {
			throw new RuleException("Phone must be numeric");
		}
		if(phone.length()<10){
			throw new RuleException("Phone must be a number of at least 10 digits");
		}
	}
	
	private void credentialsRule(AuthorController author) throws RuleException {
		String credentials = author.getCredentialsField().getText();
		if(credentials == null || credentials.equals("")){
			throw new RuleException("Credentials must be non empty");
		}
	}
	
	private void bioRule(AuthorController author) throws RuleException {
		String bio = author.getBioArea().getText();
		if(bio == null || bio.equals("")){
			throw new RuleException("The biography cannot be empty");
		}
	}
	
	private void existsRule(AuthorController author) throws RuleException {
		Author tempAuthor = new Author(author.getFirstNameField().getText(),author.getLastNameField().getText());
		if(author.getBook().getAuthors().contains(tempAuthor)){
			throw new RuleException("This book already has an author with that name");
		}
	}
}
