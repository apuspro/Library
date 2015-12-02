package mpp.aed.view.rulsets;

import mpp.aed.library.SystemController;
import mpp.aed.library.User;
import mpp.aed.view.controller.UserController;

final public class UserRuleSet implements RuleSet {
	//package level access
	UserRuleSet() {}
	@Override
	public void applyRules(Object ob) throws RuleException {
		UserController userC = (UserController)ob;
		
		usernameRule(userC);
		existRule(userC);
		passwordRule(userC);
		typeRule(userC);
		
	}
	
	private void usernameRule(UserController userC) throws RuleException {
		String username = userC.getUsernameField().getText();
		if(username == null || username.equals("")){
			throw new RuleException("Username must be non empty");
		}else{
			if(username.length()<5){
				throw new RuleException("Username should have at least 5 characters");
			}
		}
	}
	
	private void existRule(UserController userC) throws RuleException {
		String username = userC.getUsernameField().getText();
		for (User user : SystemController.getInstance().getLibrary().getUsers()) {
			if(user.getUsername().equals(username)){
				throw new RuleException("Username already exists");
			}
		}
	}
	
	private void passwordRule(UserController userC) throws RuleException {
		String password = userC.getPasswordField().getText();
		if(password == null || password.equals("")){
			throw new RuleException("Password must be non empty");
		}else{
			if(password.length()<5){
				throw new RuleException("Password should have at least 5 characters");
			}
		}
	}

	private void typeRule(UserController userC) throws RuleException {
		String combo = userC.getComboBox().getValue();
		if(combo == null || combo.contains("Select")){
			throw new RuleException("Choose the type of user");
		}
	}
}
