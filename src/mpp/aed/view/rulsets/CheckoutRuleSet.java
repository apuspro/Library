/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.rulsets;

import mpp.aed.view.controller.CheckoutController;

/**
 *
 * @author 984966
 */
public class CheckoutRuleSet implements RuleSet{

    @Override
    public void applyRules(Object ob) throws RuleException {
        CheckoutController cc = (CheckoutController)ob;
        memberIdRule(cc);
        ISBNRule(cc);
              
    }
    
    private void memberIdRule(CheckoutController cc) throws RuleException {
        if( cc.getMemberIdField().getText().trim().isEmpty() ) {
            throw new RuleException("Member Id cannot be empty");
        }
        
        try{
            Integer.parseInt(cc.getMemberIdField().getText().trim());
        } catch( NumberFormatException ex ) {
            throw new RuleException("Member Id must be numeric");
        }
    }
    
    private void ISBNRule(CheckoutController cc) throws RuleException {
		String aISBN = cc.getIsbnField().getText();
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
    
}
