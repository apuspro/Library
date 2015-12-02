package mpp.aed.view.rulsets;

import java.util.HashMap;

import mpp.aed.view.controller.AddCopyOfBookController;
import mpp.aed.view.controller.AuthorController;
import mpp.aed.view.controller.BookController;
import mpp.aed.view.controller.CheckoutController;
import mpp.aed.view.controller.CreateMemberController;
import mpp.aed.view.controller.LoginController;
import mpp.aed.view.controller.UserController;

final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Object>, RuleSet> map = new HashMap<>();
	static {
		map.put(LoginController.class, new LoginRuleSet());
		map.put(CreateMemberController.class, new CreateMemberRuleSet());
		map.put(AddCopyOfBookController.class, new AddCopyOfBookRuleSet());
		map.put(BookController.class, new BookRuleSet());
		map.put(UserController.class, new UserRuleSet());
		map.put(AuthorController.class, new AuthorRuleSet());
                map.put(CheckoutController.class, new CheckoutRuleSet());
	}
	public static RuleSet getRuleSet(Object c) {
		Class<? extends Object> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException("No RuleSet found for this Component");
		}
		return map.get(cl);
	}
}
