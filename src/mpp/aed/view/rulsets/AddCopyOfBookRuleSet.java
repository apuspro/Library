package mpp.aed.view.rulsets;

import mpp.aed.view.controller.AddCopyOfBookController;

final public class AddCopyOfBookRuleSet implements RuleSet {

	AddCopyOfBookRuleSet() {
	}

	@Override
	public void applyRules(Object ob) throws RuleException {
		ISBNRule((AddCopyOfBookController)ob);
	}
	
	private void ISBNRule(AddCopyOfBookController controller) throws RuleException {
		String aISBN = controller.getIsbnField().getText();
		if(aISBN == null || aISBN.equals("")){
			throw new RuleException("ISBN must be non empty");
		}else{
			if(aISBN.length()!=10){
				throw new RuleException("ISBN should have at lenght of 10 characters");
			}
		}
	}
}
