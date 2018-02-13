package manager.orderManager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.purchase.model.OrderBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class ordList
 */
@WebServlet("/manager/orderManager/ordList")
public class OrdList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrdList() {
		super();

	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrdManagerDao dao = (OrdManagerDao) wctx.getBean("OrdManagerDaoImpl");

		try {
			Short osId = Short.valueOf(request.getParameter("type"));
			List<OrderBean> obList = dao.getOrd(osId);
			request.setAttribute("allOrder", obList);

		} catch (Exception e) {

		} 
		
			RequestDispatcher rd = request.getRequestDispatcher("OrderManager.jsp");
			rd.forward(request, response);
			return;

	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
