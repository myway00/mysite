package com.poscodx.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.mvc.main.MainActionFactory;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.mvc.ActionFactory;

public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    public void init() throws ServletException {
        String configPath = this.getServletConfig().getInitParameter("config");
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("a");
        Action action = new MainActionFactory().getAction(actionName);
        action.execute(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
