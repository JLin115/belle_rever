package manager.orderManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.purchase.model.OrderBean;
import home.purchase.model.OrderValBean;

/**
 * Servlet implementation class ModifyItem
 */
@WebServlet("/manager/orderManager/ModifyItem")
public class ModifyItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ModifyItem() {

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String qty = request.getParameter("qty");
		String ordSerN = request.getParameter("ordSerN");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
