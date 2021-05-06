package org.kosta.semi.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontControllerServlet() {
        super();
    }
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    	/////// step1 ///////
    	String uri=request.getRequestURI();
    	//System.out.println(uri);//  /webstudy18-ex/MockController.do
    	String contextPath=request.getContextPath();
    	//System.out.println(contextPath);// /webstudy18-ex
    	String command=uri.substring(contextPath.length()+1,uri.length()-".do".length());
    	//System.out.println(command);// MockController
    	/////// step2 /////////
    	Controller controller=HandlerMapping.getInstance().create(command);
    	////// step3 /////////
    	String view=controller.execute(request, response);
    	///// step4 /////////
    	if(view.startsWith("redirect:")) {
    		response.sendRedirect(view.substring("redirect:".length()));
    	}else {
    		request.getRequestDispatcher(view).forward(request, response);
    	}
    	
    	}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("front get");
		handleRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		handleRequest(request, response);
	}
}
