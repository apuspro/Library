package mpp.aed.view.rulsets;

import java.util.HashMap;

import mpp.aed.view.controller.LoginController;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Object>, RuleSet> map = new HashMap<>();
	static {
		map.put(LoginController.class, new LoginRuleSet());
	}
	public static RuleSet getRuleSet(Object c) {
		Class<? extends Object> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
