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
import manager.itemManager.model.ItemBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class ordList
 */
@WebServlet("/manager/orderManager/OrdList")
public class OrdList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext wctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrdManagerDao dao = (OrdManagerDao) wctx.getBean("OrdManagerDaoImpl");
		int pageNow = 0;
		boolean b = true;
		Short osId=null;
		try {
			osId = Short.valueOf(request.getParameter("type"));
			pageNow = Integer.valueOf(request.getParameter("pageNow"));
			if(request.getParameter("pageNow").equals("")){
				b = false;
			}
			dao.setPageNow(pageNow);
			dao.setOsid(osId);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("正在導回主頁");
			response.sendRedirect("OrderManager.jsp");
			return;
		} 
		if (pageNow <= dao.getTotalPage()&b) {
			List<OrderBean> obList = dao.getOrd(osId);
			request.setAttribute("osId", osId);
			request.setAttribute("allOrder", obList);
			request.setAttribute("pageNow", dao.getPageNow());
			request.setAttribute("totalPage", dao.getTotalPage());
			RequestDispatcher rd = request.getRequestDispatcher("OrderManager.jsp");
			rd.forward(request, response);
			return;
		} else {
			System.out.println("正在導回主頁");
			response.sendRedirect("OrderManager.jsp");
			return;
//			RequestDispatcher rd = request.getRequestDispatcher("ItemManager.jsp");
//			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
