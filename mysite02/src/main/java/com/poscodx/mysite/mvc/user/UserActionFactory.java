package com.poscodx.mysite.mvc.user;

import com.poscodx.mysite.mvc.main.MainAction;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if ("login".equals(actionName)) {
			action = new LoginAction();
		} else if ("logout".equals(actionName)) {
			action = new LoginOutAction();
		} else if ("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		}  else if ("update".equals(actionName)) {
			action = new UpdateAction();
		}else if ("updatesuccess".equals(actionName)) {
			action = new updateSuccessAction();
		}else {
			action = new MainAction();
		}
 
		return action;
	}

}
