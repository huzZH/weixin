package com.web.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;



public class InterfaceUrlInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterfaceUrlInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servle t#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		InterfaceUrlInit.init();
	}

}
