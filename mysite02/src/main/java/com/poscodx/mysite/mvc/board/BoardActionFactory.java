package com.poscodx.mysite.mvc.board;

import com.poscodx.mysite.mvc.board.comment.CommentAction;
import com.poscodx.mysite.mvc.board.comment.CommentFormAction;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}else if("write".equals(actionName)) {
			action = new WriteAction();
		}else if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		}else if("update".equals(actionName)) {
			action = new UpdateAction();
		}else if("commentform".equals(actionName)) {
			action = new CommentFormAction();
		}else if("comment".equals(actionName)) {
			action = new CommentAction();
		}else {
			action = new ListAction();
		}
		
		return action;
	}

}