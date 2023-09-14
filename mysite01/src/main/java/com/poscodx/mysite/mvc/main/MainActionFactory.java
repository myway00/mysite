package com.poscodx.mysite.mvc.main;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
