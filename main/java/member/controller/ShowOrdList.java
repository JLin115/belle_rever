package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import home.purchase.model.OrderBean;
import home.register.model.Dao;
import home.register.model.MemberBean;

/**
 * Servlet implementation class ShowOrdList
 */

@WebServlet("/member/ShowOrdList")
public class ShowOrdList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowOrdList() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if (mb != null) {
			try {
				Short osId = Short.valueOf(request.getParameter("type"));
				Dao dao = (Dao) ctx.getBean("MemberDAOImpl");
				List<OrderBean> ordList = dao.getOrd(osId, mb.getMid());
				request.setAttribute("ordList", ordList);
				RequestDispatcher rd = request.getRequestDispatcher("OrdList.jsp");
				rd.forward(request, response);
				return;
			} catch (Exception e) {// 90%處理查詢字串有誤
				e.printStackTrace();
				response.sendRedirect("Member.jsp");
				return;
			}
		} else {// 未登入 99.9不會發生 此網頁會被filter抓去登入
			response.sendRedirect("Member.jsp");
			return;

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
