package home.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete_qyt
 */
@WebServlet("/home/Delete_qty")
public class Delete_qty extends HttpServlet {
	private static final long serialVersionUID = 1L;    
    public Delete_qty() {
        super();
  
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		
		response.sendRedirect("/Belle_Rever/home/cart/ShowCart.jsp");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
