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
import home.purchase.model.OrderValBean;
import manager.orderManager.model.OrdManagerDao;

/**
 * Servlet implementation class modifyOrd
 */
@WebServlet("/manager/orderManager/ModifyOrd")
public class ModifyOrd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyOrd() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		OrdManagerDao dao = (OrdManagerDao) ctx.getBean("OrdManagerDaoImpl");
		Integer ordId = null;
		try {
			ordId = Integer.valueOf(request.getParameter("id"));
		} catch (Exception e) {

		}
		if (ordId != null) {
			OrderBean ob = dao.getAOrd(ordId);
			List<OrderValBean> ovb = dao.getOrdVal(ordId);
			request.setAttribute("ob", ob);
			request.setAttribute("ovb", ovb);
		}

		RequestDispatcher rd = request.getRequestDispatcher("OrderModify.jsp");
		rd.forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
