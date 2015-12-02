package mpp.aed.view.rulsets;

import mpp.aed.view.controller.LoginController;

final public class LoginRuleSet implements RuleSet {
	//package level access
	LoginRuleSet() {}
	@Override
	public void applyRules(Object ob) throws RuleException {
		LoginController login = (LoginController)ob;
		
		usernameRule(login);
		passwordRule(login);
		
	}
	
	private void usernameRule(LoginController login) throws RuleException {
		String username = login.getUsernameField().getText();
		if(username == null || username.equals("")){
			throw new RuleException("ID must be non empty");
		}else{
			if(username.length()<5){
				throw new RuleException("Username should have at least 5 characters");
			}
		}
	}
	private void passwordRule(LoginController login) throws RuleException {
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
