/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpp.aed.view.rulsets;

import mpp.aed.view.controller.SearchBookController;

/**
 *
 * @author HP ENVY TS
 */
public class ISBNRuleSet implements RuleSet {

    @Override
    public void applyRules(Object ob) throws RuleException {
        SearchBookController sbc = (SearchBookController)ob;
        ISBNRule(sbc);
    }

    private void ISBNRule(SearchBookController sbc) throws RuleException {
        String aISBN = sbc.getIsbnField().getText();
        if (aISBN == null || aISBN.equals("")) {
            throw new RuleException("ISBN must be non empty");
        } else {
            if (aISBN.length() != 10) {
                throw new RuleException("ISBN should have at lenght of 10 characters");
            }
        }
    }
}
