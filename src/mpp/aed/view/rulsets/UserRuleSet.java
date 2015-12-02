package mpp.aed.view.rulsets;

import mpp.aed.library.SystemController;
import mpp.aed.library.User;
import mpp.aed.view.controller.UserController;

final public class UserRuleSet implements RuleSet {
	//package level access
	UserRuleSet() {}
	@Override
	public void applyRules(Object ob) throws RuleException {
		UserController login = (UserController)ob;
		
		usernameRule(login);
		existRule(login);
		passwordRule(login);
		
	}
	
	private void usernameRule(UserController login) throws RuleException {
		String username = login.getUsernameField().getText();
		if(username == null || username.equals("")){
			throw new RuleException("ID must be non empty");
		}else{
			if(username.length()<5){
				throw new RuleException("Username should have at least 5 characters");
			}
		}
	}
	
	private void existRule(UserController login) throws RuleException {
		String username = login.getUsernameField().getText();
		for (User user : SystemController.getInstance().getLibrary().getUsers()) {
			if(user.getUsername().equals(username)){
				throw new RuleException("Username already exists");
			}
		}
		
		if(username == null || username.equals("")){
			throw new RuleException("ID must be non empty");
		}else{
			if(username.length()<5){
				throw new RuleException("Username should have at least 5 characters");
			}
		}
	}
	
	private void passwordRule(UserController login) throws RuleException {
		String password = login.getPasswordField().getText();
		if(password == null || password.equals("")){
			throw new RuleException("Password must be non empty");
		}else{
			if(password.length()<5){
				throw new RuleException("Password should have at least 5 characters");
			}
		}
	}

}
