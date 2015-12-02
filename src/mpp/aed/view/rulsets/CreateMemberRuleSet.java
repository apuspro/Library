package mpp.aed.view.rulsets;

import mpp.aed.view.controller.CreateMemberController;

final public class CreateMemberRuleSet implements RuleSet {

	CreateMemberRuleSet() {}

	@Override
	public void applyRules(Object ob) throws RuleException {
		this.validateFields((CreateMemberController)ob);
	}
	
	private void validateFields(CreateMemberController controller) throws RuleException{
		if(controller.getMemberIdField().getText().isEmpty()){
			throw new RuleException("Member Id cannot be empty");
		}
		try {
			Integer.parseInt(controller.getMemberIdField().getText());
		} catch (NumberFormatException e) {
			throw new RuleException("Member Id must be numeric");
		}
		if(controller.getFirstNameField().getText().isEmpty()){
			throw new RuleException("First Name cannot be empty");
		}
		if(controller.getLastNameField().getText().isEmpty()){
			throw new RuleException("Last Name cannot be empty");
		}
		if(!controller.getPhoneNumberField().getText().isEmpty()){
			try {
				Integer.parseInt(controller.getPhoneNumberField().getText());
			} catch (NumberFormatException e) {
				throw new RuleException("Phone Number should be numeric");
			}
		}
	}
}
