package com.coding.web.controllers;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("num")==null) {
        	Random rand = new Random();
        	int num = rand.nextInt(99)+1;
        	session.setAttribute("num", num);        	
        	session.setAttribute("guessed", false);        	
        	session.setAttribute("cnt", 0);        	
        	session.setAttribute("lost", false);        	
        }
        System.out.println("session.getAttribute(cnt) is: "+ session.getAttribute("cnt"));
//set custom range
        if(request.getParameter("lower")!=null && request.getParameter("upper")!=null && session.getAttribute("cnt").toString().equals("0") ) {
        	Random rand2 = new Random();
        	int lower = Math.abs(Integer.parseInt(request.getParameter("lower")));
        	int upper = Integer.parseInt(request.getParameter("upper"));
        	int num = rand2.nextInt(lower+ upper)-lower;
        	session.setAttribute("num", num);        	
        }

        request.setAttribute("num", session.getAttribute("num"));
        request.setAttribute("guessed", session.getAttribute("guessed"));
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		view.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int cnt = 0;
        String guessed ="";
        String lost = "";
//set stuff
        if(session.getAttribute("guessed")!=null) {
        	guessed = session.getAttribute("guessed").toString();}
    	if(session.getAttribute("cnt") == null) {
    		cnt = 1; }
    	else {
    		cnt = (int) session.getAttribute("cnt")+1;}
    	if (session.getAttribute("lost")!=null) {
    		lost = session.getAttribute("lost").toString();
    	}
//logic 
    	if (lost.equals("false")) {    		
		    if(session.getAttribute("guessed").toString().equals("false")) {        	
			    if(session.getAttribute("num").toString().equals(request.getParameter("playerguess"))) {
			    	System.out.println("got it! ");
			    	session.setAttribute("cnt", cnt);
			    	session.setAttribute("guessed", true);}
			    else{
			    	session.setAttribute("cnt", cnt);
			    	if (cnt >=5) {
				    	session.setAttribute("lost", true);}
			    	System.out.println("nope! ");}
		    }
		    if (guessed.equals("true")) {
		        	System.out.println("You got it already");}
    	}
//reset (lost ==true)
    	if(request.getParameter("reset")!=null) {
    		session.invalidate();
		}
    	doGet(request, response);
	}

}
